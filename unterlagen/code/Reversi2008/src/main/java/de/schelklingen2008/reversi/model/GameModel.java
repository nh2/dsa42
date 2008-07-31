package de.schelklingen2008.reversi.model;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{
    public static final int SIZE = 8;

    private static final Player PLAYER_START = Player.WHITE;

    /** The initial set of pieces. */
    private static final int[] SX = { 3, 3, 4, 4 };
    private static final int[] SY = { 3, 4, 3, 4 };
    private static final Player[] SO = { Player.BLACK, Player.WHITE, Player.WHITE, Player.BLACK };
    private static final int STARTERS_COUNT = SX.length;

    /** The eight directions NW, N, NE, E, SE, S, SW, W. */
    private static final int[] DX = { -1, -1, -1, 0, 1, 1, 1, 0 };
    private static final int[] DY = { -1, 0, 1, 1, 1, 0, -1, -1 };
    private static final int DIRECTIONS_COUNT = DX.length;

    private Player[][] board;
    private Player turnHolder;

    public GameModel()
    {
        clear();
        initialize();
    }

    public GameModel(GameModel other)
    {
        board = copyBoard(other.board);
        turnHolder = other.turnHolder;
    }

    private void initialize()
    {
        for (int i = 0; i < STARTERS_COUNT; i++)
            setPiece(SX[i], SY[i], SO[i]);
        setTurnHolder(PLAYER_START);
    }

    public Player getWinner()
    {
        if (!isFinished()) return null;
        int diff = countPieces(Player.WHITE) - countPieces(Player.BLACK);
        if (diff > 0) return Player.WHITE;
        if (diff < 0) return Player.BLACK;
        return null;
    }

    private void advanceTurnHolder()
    {
        setTurnHolder(getTurnHolder().other());
        if (!hasLegalMoves()) setTurnHolder(getTurnHolder().other());
        if (!hasLegalMoves()) setTurnHolder(null); // finishes the game
    }

    /**
     * Returns true if the current turn holder has legal moves.
     */
    public boolean hasLegalMoves()
    {
        for (int y = 0; y < SIZE; y++)
            for (int x = 0; x < SIZE; x++)
                if (isLegalMove(x, y, getTurnHolder())) return true;
        return false;
    }

    /**
     * Returns true, if the given player is allowed to place a piece at the given position.
     */
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

    public boolean isFinished()
    {
        return getTurnHolder() == null;
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

    public int countPieces(Player player)
    {
        int count = 0;
        for (int y = 0; y < SIZE; y++)
            for (int x = 0; x < SIZE; x++)
                if (board[x][y] == player) count++;
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

    private static Player[][] copyBoard(Player[][] s)
    {
        Player[][] copy = new Player[SIZE][SIZE];
        for (int x = 0; x < SIZE; x++)
            for (int y = 0; y < SIZE; y++)
                copy[x][y] = s[x][y];
        return copy;
    }

    /**
     * Returns a copy of the board.
     */
    public Player[][] getBoard()
    {
        return copyBoard(board);
    }

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
}