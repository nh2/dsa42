package de.schelklingen2008.dasverruecktelabyrinth.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel implements Serializable
{

    public static final int        SIZE           = 7;
    public static final PlayerType PLAYER_START   = PlayerType.WHITE;

    private Tile[][]               board;                                                    // Spielbrett
    private PlayerType             turnHolder;                                               // Wer ist
    // dran
    private boolean                walk           = false;                                   // false =
    // Phase
    // 1

    // true = Phase2
    private Tile                   insert         = new Tile(true, true, false, false, null); // einschiebbare
    // Spielfeldkarte

    // DummyList

    private PlayerCards            dummie         = new PlayerCards();

    Map<PlayerType, Player>        player         = new HashMap<PlayerType, Player>();
    Map<PlayerType, PlayerCards>   playerCardsMap = new HashMap<PlayerType, PlayerCards>();

    // TODO PlayerCards instanzieren

    private static final int       NO_PLAYERS     = 4;

    private static final Random    RAND           = new Random();

    public GameModel(int SpielerAnzahl)
    {
        clear();
        init();
    }

    private void init()
    {
        List<Tile> tiles = generateTiles();
        Collections.shuffle(tiles);
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                board[i][j] = tiles.get(i * board.length + j);
            }
        }

        setTurnHolder(PLAYER_START);
        insert = tiles.get(board.length * board.length);

        player.put(PlayerType.GREEN, new Player(PlayerType.GREEN,
                                                RAND.nextInt(board.length),
                                                RAND.nextInt(board.length)));
        player.put(PlayerType.WHITE, new Player(PlayerType.WHITE,
                                                RAND.nextInt(board.length),
                                                RAND.nextInt(board.length)));
        player.put(PlayerType.BLACK, new Player(PlayerType.BLACK,
                                                RAND.nextInt(board.length),
                                                RAND.nextInt(board.length)));
        player.put(PlayerType.RED, new Player(PlayerType.RED, RAND.nextInt(board.length), RAND.nextInt(board.length)));

        playerCardsMap.put(PlayerType.WHITE, dummie);
    }

    private List<Tile> generateTiles()
    {

        List<Tile> temp = new ArrayList<Tile>();

        // Kreuzungen
        temp.add(new Tile(true, true, true, true, TreasureCard.EULE));
        temp.add(new Tile(true, true, true, true, null));
        temp.add(new Tile(true, true, true, true, null));
        temp.add(new Tile(true, true, true, true, TreasureCard.KRONE));
        temp.add(new Tile(true, true, true, true, null));
        temp.add(new Tile(true, true, true, true, TreasureCard.FLASCHENGEIST));
        temp.add(new Tile(true, true, true, true, null));
        temp.add(new Tile(true, true, true, true, TreasureCard.RING));
        temp.add(new Tile(true, true, true, true, null));
        temp.add(new Tile(true, true, true, true, null));
        temp.add(new Tile(true, true, true, true, TreasureCard.MOTTE));

        // Geraden

        temp.add(new Tile(true, true, false, false, TreasureCard.SPINNE));
        temp.add(new Tile(true, true, false, false, null));
        temp.add(new Tile(true, true, false, false, TreasureCard.FEE));
        temp.add(new Tile(true, true, false, false, null));
        temp.add(new Tile(true, true, false, false, null));
        temp.add(new Tile(true, true, false, false, TreasureCard.KARTE));
        temp.add(new Tile(true, true, false, false, null));
        temp.add(new Tile(true, true, false, false, TreasureCard.DRACHE));
        temp.add(new Tile(true, true, false, false, TreasureCard.BIBEL));

        temp.add(new Tile(false, false, true, true, null));
        temp.add(new Tile(false, false, true, true, TreasureCard.EIDECHSE));
        temp.add(new Tile(false, false, true, true, TreasureCard.GELDBEUTEL));
        temp.add(new Tile(false, false, true, true, null));
        temp.add(new Tile(false, false, true, true, TreasureCard.FLEDERMAUS));
        temp.add(new Tile(false, false, true, true, null));
        temp.add(new Tile(false, false, true, true, TreasureCard.TROLL));
        temp.add(new Tile(false, false, true, true, null));
        temp.add(new Tile(false, false, true, true, null));

        // Kurven

        temp.add(new Tile(false, true, true, false, null));
        temp.add(new Tile(false, true, true, false, TreasureCard.SCARABAEUS));
        temp.add(new Tile(false, true, true, false, null));
        temp.add(new Tile(false, true, true, false, TreasureCard.MAUS));
        temp.add(new Tile(false, true, true, false, null));

        temp.add(new Tile(true, false, false, true, TreasureCard.SMARAGD));
        temp.add(new Tile(true, false, false, true, null));
        temp.add(new Tile(true, false, false, true, TreasureCard.TOTENKOPF));
        temp.add(new Tile(true, false, false, true, null));
        temp.add(new Tile(true, false, false, true, TreasureCard.HELM));

        temp.add(new Tile(false, true, false, true, null));
        temp.add(new Tile(false, true, false, true, TreasureCard.LEUCHTER));
        temp.add(new Tile(false, true, false, true, null));
        temp.add(new Tile(false, true, false, true, TreasureCard.SCHMUCKKASTEN));
        temp.add(new Tile(false, true, false, true, null));

        temp.add(new Tile(true, false, true, false, TreasureCard.SCHLÜSSEL));
        temp.add(new Tile(true, false, true, false, null));
        temp.add(new Tile(true, false, true, false, TreasureCard.SCHWERT));
        temp.add(new Tile(true, false, true, false, null));
        temp.add(new Tile(true, false, true, false, TreasureCard.GESPENST));
        temp.add(new Tile(true, false, true, false, null));

        // TODO List zu ende
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

    // TODO getWinner()
    // TODO isFinished()
    // TODO hasLegalMoved()
    // TODO getWinner()

    public Player getWinner()
    {
        if (!isFinished()) return null;

        return null;
    }

    public boolean isWinner(Player player)
    {
        return getWinner() == player;
    }

    private void placePlayer(Player pPlayer)
    {
        int x = (int) Math.random() * board.length;
        int y = (int) Math.random() * board.length;

        pPlayer.setXKoordinate(x);
        pPlayer.setYKoordinate(y);
    }

    private void placePlayer(int x, int y, Player pPlayer)
    {   
        if ( findWay(board, ))
        pPlayer.setXKoordinate(x);
        pPlayer.setYKoordinate(y);
    }

    public boolean isLegalMove(int x, int y, Player player)
    {
        if (isFinished()) return false;
        if (!isInBounds(x, y)) return false;

        if (!isTurnHolder(player)) return false;
        //    
        // for (int direction = 0; direction < DIRECTIONS_COUNT; direction++)
        // {
        // int captureCount = countCapturedPieces(x, y, player, direction);
        // if (captureCount > 0) return true;
        // }
        return false;
    }

    private boolean isTurnHolder(Player player2)
    {

        return false;
    }

    public GameModel(GameModel other)
    {
        board = copyBoard(other.board);
        turnHolder = other.turnHolder;
    }

    public Tile rechtsDrehen(Tile pT){
        if (pT.isCross() == true) return pT;
        if (pT.isCurve1()) == true) pT.
        
        
        
        
    }

    private void advanceTurnHolder()
    {
        // setTurnHolder(getTurnHolder().other());
        // if (!hasLegalMoves()) setTurnHolder(getTurnHolder().other());
        // if (!hasLegalMoves()) setTurnHolder(null); // finishes the game
    }

    public boolean hasLegalMoves()
    {
        for (int y = 0; y < SIZE; y++)
            for (int x = 0; x < SIZE; x++)
                if (isLegalMove(x, y, getTurnHolder())) return true;
        return false;
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

    public Map<PlayerType, PlayerCards> getPlayerCardsMap()
    {

        return playerCardsMap;
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

    public void amountOfPlayers()
    {

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

    public Tile getInsertTile()
    {
        return insert;
    }

    public Collection<Player> getPlayers()
    {
        return player.values();
    }

}