package de.schelklingen2008.reversi.ai.strategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.schelklingen2008.reversi.ai.evaluation.AlphabetaAmorEvaluationFunction;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class Copy_2_of_AlphabetaAmorStrategy implements ReversiStrategy
{

    private final AlphabetaAmorEvaluationFunction evalFunction;
    private final int                depth;
    private int                      count;
    Player                           player;

    List<HelpGameModel> hgmlist;

    public Copy_2_of_AlphabetaAmorStrategy(int depth)
    {
        this.depth = depth;
        evalFunction = new AlphabetaAmorEvaluationFunction();
    }

    public int getCount()
    {
        return count;
    }

    public Piece move(GameModel gameModel)
    {

        player = gameModel.getTurnHolder();
        Piece bestMove = null;
        List<Piece> legalMoves = new ArrayList<Piece>(gameModel.getLegalMovesSet(gameModel.getTurnHolder()));
        Integer[] values = new Integer[legalMoves.size()];
        List<SecondRunner> threads = new ArrayList<SecondRunner>(legalMoves.size());
        hgmlist = new ArrayList<HelpGameModel>(legalMoves.size());

        for (int i = 0; i < legalMoves.size(); i++)
        {
            hgmlist.add(new HelpGameModel(gameModel));
            HelpGameModel tmpgm = hgmlist.get(i);
            threads.add(new SecondRunner(tmpgm, legalMoves.get(i), values, i));
            threads.get(i).start();
        }

        try
        {
            for (SecondRunner sr : threads)
                sr.join();
        }
        catch (InterruptedException e)
        {
        }

        int bester = Integer.MIN_VALUE;
        int pos = 0;
        for (int i = 0; i < values.length; i++)
            if (values[i] > bester)
            {
                bester = values[i];
                pos = i;
            }

        bestMove = legalMoves.get(pos);

        return bestMove;
    }

    class SecondRunner extends Thread
    {

        HelpGameModel         game;
        Piece             piece;
        private Integer[] values;
        private int       pos;

        public SecondRunner(HelpGameModel game, Piece piece, Integer[] values, int pos)
        {
            this.game = game;
            this.piece = piece;
            this.values = values;
            this.pos = pos;
        }

        @Override
        public void run()
        {
            HelpGameModel clone;
            clone = new HelpGameModel();
            clone.clone(game);
            clone.placePiece(piece);
            values[pos] = min(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, clone, pos);
        }
    };

    private int max(int depth, int alpha, int beta, HelpGameModel gameModel, int pos)
    {

        List<pieceInt> liste = new weightListe(false);

        if (depth == 0 || gameModel.isFinished())
        {
            return evalFunction.evaluatePosition(gameModel, player);
        }

        for (Piece piece : gameModel.getLegalMovesSet(gameModel.getTurnHolder()))
        {
            HelpGameModel clone = hgmlist.get(pos);
            clone.clone(gameModel);
            clone.placePiece(piece);
            liste.add(new pieceInt(false, piece, evalFunction.evaluatePosition(clone, player)));
        }
        Collections.sort(liste);

        for (int i = 0; i < liste.size(); i++)
        {

            HelpGameModel clone = hgmlist.get(pos);
            clone.clone(gameModel);
            clone.placePiece(liste.get(i).piece);

            alpha = Math.max(alpha, min(depth - 1, alpha, beta, clone, pos));

            if (alpha >= beta)
            {
                return beta;
            }

        }

        return alpha;

    }

    public class weightListe extends ArrayList<pieceInt>
    {

        private boolean min;

        public weightListe(boolean min)
        {
            this.min = min;
        }
    }

    public class pieceInt implements Comparable<pieceInt>
    {

        public int     wert;
        public Piece   piece;
        public boolean min;

        public pieceInt(boolean min, Piece piece, int wert)
        {
            this.wert = wert;
            this.piece = piece;
            this.min = min;

        }

        public int compareTo(pieceInt o)
        {
            if (min)
            {
                return wert - o.wert;
            }
            return o.wert - wert;
        }

    }

    private int min(int depth, int alpha, int beta, HelpGameModel gameModel, int pos)

    {
        List<pieceInt> liste = new weightListe(true);

        if (depth == 0 || gameModel.isFinished())
        {
            return evalFunction.evaluatePosition(gameModel, player);
        }

        for (Piece piece : gameModel.getLegalMovesSet(gameModel.getTurnHolder()))
        {
            HelpGameModel clone = hgmlist.get(pos);
            clone.clone(gameModel);
            clone.placePiece(piece);
            liste.add(new pieceInt(true, piece, evalFunction.evaluatePosition(clone, player)));

        }
        Collections.sort(liste);

        for (int i = 0; i < liste.size(); i++)
        {

            HelpGameModel clone = hgmlist.get(pos);
            clone.clone(gameModel);
            clone.placePiece(liste.get(i).piece);

            beta = Math.min(beta, max(depth - 1, alpha, beta, clone, pos));

            if (beta <= alpha)
            {
                return alpha;
            }

        }

        return beta;

    }


    public class HelpGameModel implements Serializable
    {

        public static final int       SIZE             = 8;

        private final Player   PLAYER_START     = Player.WHITE;

        /** The initial set of pieces. */
        private final int[]    SX               = { 3, 3, 4, 4 };
        private final int[]    SY               = { 3, 4, 3, 4 };
        private final Player[] SO               = { Player.BLACK, Player.WHITE, Player.WHITE, Player.BLACK };
        private final int      STARTERS_COUNT   = SX.length;

        /** The eight directions NW, N, NE, E, SE, S, SW, W. */
        private final int[]    DX               = { -1, -1, -1, 0, 1, 1, 1, 0 };
        private final int[]    DY               = { -1, 0, 1, 1, 1, 0, -1, -1 };
        private final int      DIRECTIONS_COUNT = DX.length;


        public Player[][]            board;
        public Player                turnHolder;

        public HelpGameModel()
        {}

        public HelpGameModel(GameModel other)
        {
            board = other.getBoard();
            turnHolder = other.getTurnHolder();
        }

        public void clone(HelpGameModel other)
        {
            for (int x = 0; x < SIZE; x++)
                for (int y = 0; y < SIZE; y++)
                    board[x][y] = other.board[x][y];
            turnHolder = other.turnHolder;
        }

        private void advanceTurnHolder()
        {
            setTurnHolder(getTurnHolder().other());
            if (!hasLegalMoves()) setTurnHolder(getTurnHolder().other());
            if (!hasLegalMoves()) setTurnHolder(null); // finishes the game
        }

        public boolean hasLegalMoves()
        {
            for (int y = 0; y < SIZE; y++)
                for (int x = 0; x < SIZE; x++)
                    if (isLegalMove(x, y, getTurnHolder())) return true;
            return false;
        }

        public void placePiece(Piece piece)
        {
            placePiece(piece.getX(), piece.getY(), piece.getPlayer());
        }

        /**
         * Places a piece at the given position and flips the captured pieces.
         */
        public void placePiece(int x, int y, Player player)
        {
            if (!isLegalMove(x, y, player)) throw new IllegalArgumentException("illegal move");

            setPiece(x, y, player);

            for (int direction = 0; direction < DIRECTIONS_COUNT; direction++)
            {
                int captureCount = countCapturedPieces(x, y, player, direction);
                flipPieces(x, y, direction, captureCount);
            }

            advanceTurnHolder();
        }
        public boolean isFinished()
        {
            return getTurnHolder() == null;
        }

        public boolean isLegalMove(int x, int y, Player player)
        {
            if (isFinished()) return false;
            if (!isInBounds(x, y)) return false;
            if (isOccupied(x, y)) return false;
            if (!isTurnHolder(player)) return false;

            for (int direction = 0; direction < DIRECTIONS_COUNT; direction++)
            {
                int captureCount = countCapturedPieces(x, y, player, direction);
                if (captureCount > 0) return true;
            }
            return false;
        }

        private int countCapturedPieces(int pieceX, int pieceY, Player player, int direction)
        {
            int x = pieceX + DX[direction];
            int y = pieceY + DY[direction];
            int captureCount = 0;
            while (isInBounds(x, y) && getPlayer(x, y) == player.other())
            {
                captureCount++;
                x += DX[direction];
                y += DY[direction];
            }
            if (isInBounds(x, y) && getPlayer(x, y) == player) return captureCount;
            return 0;
        }

        private void flipPieces(int pieceX, int pieceY, int direction, int count)
        {
            int x = pieceX + DX[direction];
            int y = pieceY + DY[direction];
            for (int j = 0; j < count; j++)
            {
                flip(x, y);
                x += DX[direction];
                y += DY[direction];
            }
        }

        public boolean[][] getLegalMoves(Player player)
        {
            boolean[][] result = new boolean[SIZE][SIZE];
            if (getTurnHolder() != player) return result;
            for (int x = 0; x < SIZE; x++)
                for (int y = 0; y < SIZE; y++)
                    result[x][y] = isLegalMove(x, y, player);
            return result;
        }

        public boolean isTurnHolder(Player player)
        {
            return player == getTurnHolder();
        }

        public boolean isDraw()
        {
            return isFinished() && getWinner() == null;
        }

        public boolean isWinner(Player player)
        {
            return getWinner() == player;
        }
        public Player getWinner()
        {
            if (!isFinished()) return null;
            int diff = countPieces(Player.WHITE) - countPieces(Player.BLACK);
            if (diff > 0) return Player.WHITE;
            if (diff < 0) return Player.BLACK;
            return null;
        }
        public int countPieces(Player player)
        {
            int count = 0;
            for (int y = 0; y < SIZE; y++)
                for (int x = 0; x < SIZE; x++)
                    if (board[x][y] == player) count++;
            return count;
        }

        public int countFree()
        {
            int count = 0;
            for (int y = 0; y < SIZE; y++)
                for (int x = 0; x < SIZE; x++)
                    if (board[x][y] == null) count++;
            return count;
        }

        private void flip(int x, int y)
        {
            if (!isInBounds(x, y)) throw new IllegalArgumentException("out of bounds");
            if (!isOccupied(x, y)) throw new IllegalArgumentException("no piece to flip");
            board[x][y] = board[x][y].other();
        }

        public Player getPlayer(int x, int y)
        {
            return board[x][y];
        }

        public boolean isOccupied(int x, int y)
        {
            return getPlayer(x, y) != null;
        }

        private boolean isInBounds(int x, int y)
        {
            return x >= 0 && y >= 0 && x < SIZE && y < SIZE;
        }

        private void setPiece(int x, int y, Player player)
        {
            board[x][y] = player;
        }

        //        private static Player[][] copyBoard(Player[][] s)
        //        {
        //            Player[][] copy = new Player[SIZE][SIZE];
        //            for (int x = 0; x < SIZE; x++)
        //                for (int y = 0; y < SIZE; y++)
        //                    copy[x][y] = s[x][y];
        //            return copy;
        //        }

        /**
         * Returns a copy of the board.
         */
        //        public Player[][] getBoard()
        //        {
        //            return copyBoard(board);
        //        }

        public Player getTurnHolder()
        {
            return turnHolder;
        }

        public boolean setTurnHolder(Player turnHolder)
        {
            boolean changed = this.turnHolder != turnHolder;
            this.turnHolder = turnHolder;
            return changed;
        }

        public void setBoard(Player[][] board)
        {
            this.board = board;
        }

        private void clear()
        {
            board = new Player[SIZE][SIZE];
            turnHolder = null;
        }

        public Set<Piece> getLegalMovesSet()
        {
            return getLegalMovesSet(getTurnHolder());
        }

        public Set<Piece> getLegalMovesSet(Player player)
        {
            Set<Piece> legalMoves = new HashSet<Piece>();
            for (int x = 0; x < GameModel.SIZE; x++)
                for (int y = 0; y < GameModel.SIZE; y++)
                    if (isLegalMove(x, y, player)) legalMoves.add(new Piece(x, y, player));

            return legalMoves;
        }
    }
}
