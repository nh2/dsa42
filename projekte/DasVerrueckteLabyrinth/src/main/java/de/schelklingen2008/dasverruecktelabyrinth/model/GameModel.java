package de.schelklingen2008.dasverruecktelabyrinth.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.text.Position;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel implements Serializable
{

    private static final PlayerType[] TYPES          = new PlayerType[] { PlayerType.GREEN, PlayerType.WHITE,
            PlayerType.BLACK, PlayerType.RED        };

    public static final int           SIZE           = 7;
    public static final PlayerType    PLAYER_START   = PlayerType.WHITE;

    private Tile[][]                  board;                                                    // Spielbrett
    private PlayerType                turnHolder;                                               // Wer ist
    // dran
    private boolean                   walk           = false;                                   // false =
    // Phase
    // 1

    // true = Phase2
    private Tile                      insert         = new Tile(true, true, false, false, null); // einschiebbare
    // Spielfeldkarte

    // DummyList

    private PlayerCards               dummie         = new PlayerCards();

    Map<PlayerType, Player>           player         = new HashMap<PlayerType, Player>();
    Map<PlayerType, PlayerCards>      playerCardsMap = new HashMap<PlayerType, PlayerCards>();

    // TODO PlayerCards instanzieren

    private static final Random       RAND           = new Random();

    public GameModel(String[] names)
    {
        clear();
        init(names);
    }

    private void init(String[] names)
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

        for (int i = 0; i < names.length; i++)
        {
            int x = RAND.nextInt(board.length);
            int y = RAND.nextInt(board.length);
            player.put(TYPES[i], new Player(TYPES[i], names[i], x, y));
        }

        generateTreasureCards();
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

        return temp;
    }

    private void generateTreasureCards()
    {
        List<TreasureCard> temp = new ArrayList<TreasureCard>();

        temp.add(TreasureCard.BIBEL);
        temp.add(TreasureCard.DRACHE);
        temp.add(TreasureCard.EIDECHSE);
        temp.add(TreasureCard.EULE);
        temp.add(TreasureCard.FEE);
        temp.add(TreasureCard.FLASCHENGEIST);
        temp.add(TreasureCard.FLEDERMAUS);
        temp.add(TreasureCard.GELDBEUTEL);
        temp.add(TreasureCard.GESPENST);
        temp.add(TreasureCard.HELM);
        temp.add(TreasureCard.KARTE);
        temp.add(TreasureCard.KRONE);
        temp.add(TreasureCard.LEUCHTER);
        temp.add(TreasureCard.MAUS);
        temp.add(TreasureCard.MOTTE);
        temp.add(TreasureCard.RING);
        temp.add(TreasureCard.SCARABAEUS);
        temp.add(TreasureCard.SCHLÜSSEL);
        temp.add(TreasureCard.SCHMUCKKASTEN);
        temp.add(TreasureCard.SCHWERT);
        temp.add(TreasureCard.SMARAGD);
        temp.add(TreasureCard.SPINNE);
        temp.add(TreasureCard.TOTENKOPF);
        temp.add(TreasureCard.TROLL);

        Collections.shuffle(temp);
        int noOfPlayers = getPlayers().size();
        int cardsPerPlayer = 24 / noOfPlayers;
        getPlayers();

        if (noOfPlayers > 1)
        {
            PlayerCards temp2 = new PlayerCards();
            PlayerCards temp3 = new PlayerCards();

            for (int i = 0; i < cardsPerPlayer; i++)
            {
                temp2.getHiddenCards().add(i, null);
            }
            playerCardsMap.put(PlayerType.WHITE, temp2);

            for (int i = cardsPerPlayer; i < 2 * cardsPerPlayer; i++)
            {
                temp3.getHiddenCards().add(i, null);
            }
            playerCardsMap.put(PlayerType.BLACK, temp3);

            if (noOfPlayers > 2)
            {

                PlayerCards temp4 = new PlayerCards();

                for (int i = cardsPerPlayer * 2; i < 3 * cardsPerPlayer; i++)
                {
                    temp4.getHiddenCards().add(i, null);
                }
                playerCardsMap.put(PlayerType.RED, temp4);

                if (noOfPlayers > 3)
                {
                    PlayerCards temp5 = new PlayerCards();
                    for (int i = cardsPerPlayer * 3; i < 4 * cardsPerPlayer; i++)
                    {
                        temp5.getHiddenCards().add(i, null);
                    }
                    playerCardsMap.put(PlayerType.GREEN, temp5);

                }

            }
        }

    }

    // WHITE, BLACK, RED, GREEN;

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

    private void placedOnSearchCard()
    {
        hiddenCards();
        List<TreasureCard> hidden = hiddenCards.getHiddenCards();
        TreasureCard searchThisCard = hidden.get(0);

    }

    public void placePlayer(int x, int y, PlayerType pPlayerType)
    {
        Player pPlayer = player.get(pPlayerType);
        if (isLegalMove(x, y, pPlayer))
        {

            pPlayer.setXKoordinate(x);
            pPlayer.setYKoordinate(y);
        }
        changeTurnholder();
    }

    public List<Position> findWay(Tile[][] pBoard, int sx, int sy, int ex, int ey, int lx, int ly)
    {

        return null;
    }

    public boolean isLegalMove(int x, int y, Player player)
    {
        boolean temp = false;
        if (isFinished())
        ;
        if (!isInBounds(x, y))
        ;

        if (!isTurnHolder(player))
        ;
        if (findWay(board, player.getXKoordinate(), player.getYKoordinate(), x, y, player.getXKoordinate(),
                    player.getYKoordinate()) != null) temp = true;

        return temp;
    }

    private boolean isTurnHolder(Player player2)
    {
        boolean temp = false;
        if (player2.getPlayerType() == turnHolder) temp = true;

        return temp;
    }

    public void changeTurnholder()
    {
        if (turnHolder == PlayerType.WHITE) turnHolder = PlayerType.BLACK;
        if (turnHolder == PlayerType.BLACK) turnHolder = PlayerType.RED;
        if (turnHolder == PlayerType.RED) turnHolder = PlayerType.GREEN;
        if (turnHolder == PlayerType.GREEN) turnHolder = PlayerType.WHITE;

    }

    public GameModel(GameModel other)
    {
        board = copyBoard(other.board);
        turnHolder = other.turnHolder;
    }

    public Tile rechtsDrehen()
    {

        insert.turnRight();
        return insert;
    }

    public Tile linksDrehen()
    {
        insert.turnLeft();
        return insert;
    }

    public void insert(PushButton pPushButton)
    {
        if (walk == false)
        {
            if (pPushButton == PushButton.SuedLinks) einschiebenSued(1, 6);
            if (pPushButton == PushButton.SuedMitte) einschiebenSued(3, 6);
            if (pPushButton == PushButton.SuedRechts) einschiebenSued(5, 6);
            if (pPushButton == PushButton.NordLinks) einschiebenNord(1, 0);
            if (pPushButton == PushButton.NordMitte) einschiebenNord(3, 0);
            if (pPushButton == PushButton.NordRechts) einschiebenNord(5, 0);
            if (pPushButton == PushButton.OstUnten) einschiebenOst(6, 5);
            if (pPushButton == PushButton.OstMitte) einschiebenOst(6, 3);
            if (pPushButton == PushButton.OstOben) einschiebenOst(6, 1);
            if (pPushButton == PushButton.WestUnten) einschiebenWest(0, 5);
            if (pPushButton == PushButton.WestMitte) einschiebenWest(0, 3);
            if (pPushButton == PushButton.WestOben) einschiebenWest(0, 1);
        }
    }

    private void einschiebenNord(int x, int y)
    {
        Tile temp = insert;
        insert = board[x][board.length - 1];
        for (int i = board.length - 1; i < 1; i--)
        {
            board[x][i] = board[x][i - 1];
        }
        board[x][0] = temp;
    }

    private void einschiebenSued(int x, int y)
    {
        Tile temp = insert;
        insert = board[x][0];
        for (int i = 0; i < board.length - 1; i++)
        {
            board[x][i] = board[x][i + 1];
        }
        board[x][(board.length - 1)] = temp;

    }

    private void einschiebenOst(int x, int y)
    {

        Tile temp = insert;
        insert = board[0][y];
        for (int i = 0; i < board.length - 1; i++)
        {
            board[i][y] = board[i - 1][y];
        }
        board[(board.length - 1)][y] = temp;
    }

    private void einschiebenWest(int x, int y)
    {
        Tile temp = insert;
        insert = board[board.length - 1][y];
        for (int i = board.length - 1; i < 1; i--)
        {
            board[i][y] = board[i - 1][y];
        }
        board[0][y] = temp;
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
                if (isLegalMove(x, y, player.get(getTurnHolder()))) return true;
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
                result[x][y] = isLegalMove(x, y, this.player.get(player));
        return result;
    }

    public PlayerType getTurnHolder()
    {
        return turnHolder;
    }

    public boolean isFinished()
    {
        boolean temp = false;
        if (playerCardsMap.get(turnHolder).getHiddenCards() == null) temp = true;
        turnHolder = null;
        return temp;
    }

    public Tile getInsertTile()
    {
        return insert;
    }

    public Collection<Player> getPlayers()
    {
        return player.values();
    }

    public String getName(PlayerType playerType)
    {
        Player p = player.get(playerType);
        if (p == null) return null;
        return p.getName();
    }

    public Player getPlayer(String name)
    {
        if (name == null) return null;
        for (Player p : player.values())
            if (name.equals(p.getName())) return p;
        return null;
    }
}