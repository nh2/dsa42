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
        int x = (int) Math.random()* board.length;
        int y = (int) Math.random()* board.length;
        
        pPlayer
    }

    private void clear()
    {

    }

    private void setTurnHolder(PlayerType pPlayer)
    {

        turnHolder = pPlayer;
    }

    public boolean isFinished()
    {
        return false;
    }
}