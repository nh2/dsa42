package de.schelklingen2008.dasverruecktelabyrinth.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    private static final int        SIZE         = 7;
    private static final PlayerType PLAYER_START = PlayerType.WHITE;

    private Tile[][]                board;                                                // Spielbrett
    private PlayerType              turnHolder;                                           // Wer ist dran
    private boolean                 walk         = false;                                 // false = Phase 1
    // true = Phase2
    private Tile                    insert;                                               // einschiebbare
    // Spielfeldkarte

    Map<PlayerType, Player>         player       = new HashMap<PlayerType, Player>();
    Map<PlayerType, PlayerCards>    playerTypes  = new HashMap<PlayerType, PlayerCards>();

    public GameModel()
    {
        clear();
        init();
    }

    private void init()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {

                for (int i1 = 0; i1 < generateTiles().size(); i1++)
                {

                    int r = (int) (Math.random() * generateTiles().size());
                    board[i][j] = generateTiles().get(r);
                    generateTiles().remove(r);
                }
            }
        }
        setTurnHolder(PLAYER_START);
        int r = generateTiles().size();
        insert = generateTiles().get(r);
        generateTiles().remove(r);

    }

    private List<Tile> generateTiles()
    {

        List<Tile> temp = new ArrayList<Tile>();

        return temp;
    }

    private void setUnmoveable(Tile[][] pBoard)
    {
        for (int i = 0; i < pBoard.length; i++)
        {
            for (int j = 0; j < pBoard.length; j++)
            {
                if (i % 2 == 0 && j % 2 == 0)
                {
                    pBoard[i][j].setUnmoveable(true);
                }
            }
        }
    }

    private void placePlayer(Player pPlayer)
    {
        int x = (int) Math.random() * board.length;
        int y = (int) Math.random() * board.length;

        pPlayer.setXKoordinate(x);
        pPlayer.setYKoordinate(y);
    }

    private void clear()
    {
        board = new Tile[SIZE][SIZE];
        turnHolder = null;
    }

    public Tile[][] getBoard()
    {
        return copyBoard(board);
    }

    private static Tile[][] copyBoard(Tile[][] s)
    {
        Tile[][] copy = new Tile[SIZE][SIZE];
        for (int x = 0; x < SIZE; x++)
            for (int y = 0; y < SIZE; y++)
                copy[x][y] = s[x][y];
        return copy;
    }

    public void setBoard(Tile[][] board)
    {
        this.board = board;
    }

    public boolean setTurnHolder(PlayerType turnHolder)
    {
        boolean changed = this.turnHolder != turnHolder;
        this.turnHolder = turnHolder;
        return changed;
    }

    private boolean isInBounds(int x, int y)
    {
        return x >= 0 && y >= 0 && x < SIZE && y < SIZE;
    }

    public boolean[][] getLegalMoves(PlayerType player)
    {
        boolean[][] result = new boolean[SIZE][SIZE];
        if (getTurnHolder() != player) return result;
        for (int x = 0; x < SIZE; x++)
            for (int y = 0; y < SIZE; y++)
                result[x][y] = isLegalMove(x, y, player);
        return result;
    }

    public boolean isLegalMove(int pX, int pY, PlayerType pPlayer)
    {
        boolean temp = false;
        if (isFinished()) temp = false;
        if (!isInBounds(pX, pY)) temp = false;
        return temp;
    }

    public PlayerType getTurnHolder()
    {
        return turnHolder;
    }

    public boolean isFinished()
    {
        return false;
    }

}