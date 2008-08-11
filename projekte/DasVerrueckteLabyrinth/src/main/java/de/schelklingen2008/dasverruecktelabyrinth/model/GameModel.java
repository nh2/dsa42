package de.schelklingen2008.dasverruecktelabyrinth.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel implements Serializable
{

    private static final PlayerType[] TYPES          = new PlayerType[] { PlayerType.WHITE, PlayerType.BLACK,
            PlayerType.RED, PlayerType.GREEN        };

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

        setUnmoveable(board);

        setTurnHolder(PLAYER_START);
        insert = tiles.get(board.length * board.length);

        for (int i = 0; i < names.length; i++)
        {
            int x = RAND.nextInt(board.length);
            int y = RAND.nextInt(board.length);
            player.put(TYPES[i], new Player(TYPES[i], names[i], x, y)); // TODO brauch ich placePlayerStart
            // noch?
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

            for (int i = 0, j = 0; i < cardsPerPlayer; i++, j++)
            {

                temp2.getHiddenCards().add(j, temp.get(i));
            }
            playerCardsMap.put(PlayerType.WHITE, temp2);

            for (int i = cardsPerPlayer, j = 0; i < 2 * cardsPerPlayer; i++, j++)
            {

                temp3.getHiddenCards().add(j, temp.get(i));
            }
            playerCardsMap.put(PlayerType.BLACK, temp3);

            if (noOfPlayers > 2)
            {

                PlayerCards temp4 = new PlayerCards();

                for (int i = cardsPerPlayer * 2, j = 0; i < 3 * cardsPerPlayer; i++, j++)
                {
                    temp4.getHiddenCards().add(j, temp.get(i));
                }
                playerCardsMap.put(PlayerType.RED, temp4);

                if (noOfPlayers > 3)
                {
                    PlayerCards temp5 = new PlayerCards();
                    for (int i = cardsPerPlayer * 3, j = 0; i < 4 * cardsPerPlayer; i++, j++)
                    {
                        temp5.getHiddenCards().add(j, temp.get(i));
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

    // TODO hasLegalMoved()

    public Player getWinner()
    {
        if (!isFinished()) return null;

        List<TreasureCard> hiddenWhite = playerCardsMap.get(PlayerType.WHITE).getHiddenCards();
        List<TreasureCard> hiddenBlack = playerCardsMap.get(PlayerType.BLACK).getHiddenCards();
        List<TreasureCard> hiddenRed = playerCardsMap.get(PlayerType.RED).getHiddenCards();
        List<TreasureCard> hiddenGreen = playerCardsMap.get(PlayerType.GREEN).getHiddenCards();

        if (hiddenWhite == null) return player.get(PlayerType.WHITE);
        if (hiddenBlack == null) return player.get(PlayerType.BLACK);
        if (hiddenRed == null) return player.get(PlayerType.RED);
        if (hiddenGreen == null) return player.get(PlayerType.GREEN);

        return null;

    }

    public boolean isWinner(Player player)
    {
        return getWinner() == player;
    }

    // fragt ab, ob der Spieler seine TreasurCard gefunden hat und verschiebt sie in den openCards Stapel
    private void placedOnSearchCard(Player pPlayer)
    {
        int x = pPlayer.getXKoordinate();
        int y = pPlayer.getYKoordinate();

        if (board[x][y].getTC() == null) return;

        PlayerCards playerCards = playerCardsMap.get(player.get(pPlayer));

        if (board[x][y].getTC() != playerCards.getFirstHidden()) return;

        playerCards.moveFirstHiddenToOpen();

    }

    private void placePlayerStart(Player pPlayer)
    {
        int x = (int) Math.random() * board.length;
        int y = (int) Math.random() * board.length;

        pPlayer.setXKoordinate(x);
        pPlayer.setYKoordinate(y);
    }

    // setzt den Turnholder im normalen Spiel
    public void placePlayer(int x, int y, PlayerType pPlayerType)
    {
        if (walk)
        {
            Player pPlayer = player.get(pPlayerType);
            if (isLegalMove(x, y, pPlayer))
            {
                pPlayer.setXKoordinate(x);
                pPlayer.setYKoordinate(y);
                placedOnSearchCard(pPlayer);
            }
            changeTurnHolder();
        }
    }

    // setzt den herausgeschobenen Player -egal ob turnHolder oder nicht- auf der anderen Seite wieder ins
    // Spiel
    public void placePlayerOutOfBounds(int x, int y, PlayerType pPlayerType)
    {
        Player temp = player.get(pPlayerType);

        temp.setXKoordinate(x);
        temp.setYKoordinate(y);
    }

    // prüft, ob der Weg von start bis ende frei ist
    public List<Position> findWay(int sx, int sy, int ex, int ey)
    {
        List<Position> way = findWay(new Position(sx, sy), new Position(ex, ey), new Stack<Position>());
        if (way != null) Collections.reverse(way);
        return way;
    }

    private List<Position> findWay(Position start, Position end, Stack<Position> last)
    {
        List<Position> result = null;

        if (start.equals(end))
        {
            result = new ArrayList<Position>();
            result.add(start);
            return result;
        }

        last.push(start);

        Position right = start.incX();
        Position left = start.decX();
        Position up = start.decY();
        Position down = start.incY();
        if (isInBounds(right.getX(), right.getY())
            && !last.contains(right)
            && board[start.getX()][start.getY()].getRight()
            && board[right.getX()][right.getY()].getLeft())
            result = findWay(right, end, last);
        else if (isInBounds(left.getX(), left.getY())
                 && !last.contains(left)
                 && board[start.getX()][start.getY()].getLeft()
                 && board[left.getX()][left.getY()].getRight())
            result = findWay(left, end, last);
        else if (isInBounds(up.getX(), up.getY())
                 && !last.contains(up)
                 && board[start.getX()][start.getY()].getUp()
                 && board[up.getX()][up.getY()].getDown())
            result = findWay(up, end, last);
        else if (isInBounds(down.getX(), down.getY())
                 && !last.contains(down)
                 && board[start.getX()][start.getY()].getDown()
                 && board[down.getX()][down.getY()].getUp()) result = findWay(down, end, last);

        last.pop();
        if (result == null) return null;
        result.add(start);
        return result;
    }

    public boolean isLegalMove(int x, int y, Player player)
    {
        if (isFinished()) return false;
        if (!isInBounds(x, y)) return false;
        if (!isTurnHolder(player)) return false;

        List<Position> way = findWay(player.getXKoordinate(), player.getYKoordinate(), x, y);
        if (way == null) return false;

        return true;
    }

    private boolean isTurnHolder(Player player2)
    {
        boolean temp = false;
        if (player2.getPlayerType() == turnHolder) temp = true;

        return temp;
    }

    public void changeTurnHolder()
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

    public void rechtsDrehen()
    {
        insert.turnRight();

    }

    public void linksDrehen()
    {
        insert.turnLeft();

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
        walk = true;
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
        if (player.get(PlayerType.WHITE).getXKoordinate() == x
            && player.get(PlayerType.WHITE).getYKoordinate() == board.length - 1)
        {
            placePlayerOutOfBounds(x, 0, PlayerType.WHITE);
        }
        if (player.get(PlayerType.BLACK).getXKoordinate() == x && player.get(PlayerType.BLACK).getYKoordinate() == 6)
        {
            placePlayerOutOfBounds(x, 0, PlayerType.BLACK);
        }
        if (player.get(PlayerType.RED).getXKoordinate() == x && player.get(PlayerType.RED).getYKoordinate() == 6)
        {
            placePlayerOutOfBounds(x, 0, PlayerType.RED);
        }
        if (player.get(PlayerType.GREEN).getXKoordinate() == x && player.get(PlayerType.GREEN).getYKoordinate() == 6)
        {
            placePlayerOutOfBounds(x, 0, PlayerType.GREEN);
        }
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

        if (player.get(PlayerType.WHITE).getXKoordinate() == x && player.get(PlayerType.WHITE).getYKoordinate() == 0)
        {
            placePlayerOutOfBounds(x, 6, PlayerType.WHITE);
        }

        if (player.get(PlayerType.BLACK).getXKoordinate() == x && player.get(PlayerType.BLACK).getYKoordinate() == 0)
        {
            placePlayerOutOfBounds(x, 6, PlayerType.BLACK);
        }

        if (player.get(PlayerType.RED).getXKoordinate() == x && player.get(PlayerType.RED).getYKoordinate() == 0)
        {
            placePlayerOutOfBounds(x, 6, PlayerType.RED);
        }

        if (player.get(PlayerType.GREEN).getXKoordinate() == x && player.get(PlayerType.GREEN).getYKoordinate() == 0)
        {
            placePlayerOutOfBounds(x, 6, PlayerType.GREEN);
        }

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

        if (player.get(PlayerType.WHITE).getXKoordinate() == 0 && player.get(PlayerType.WHITE).getYKoordinate() == y)
        {
            placePlayerOutOfBounds(6, y, PlayerType.WHITE);
        }
        if (player.get(PlayerType.BLACK).getXKoordinate() == 0 && player.get(PlayerType.BLACK).getYKoordinate() == y)
        {
            placePlayerOutOfBounds(6, y, PlayerType.BLACK);
        }
        if (player.get(PlayerType.RED).getXKoordinate() == 0 && player.get(PlayerType.RED).getYKoordinate() == y)
        {
            placePlayerOutOfBounds(6, y, PlayerType.RED);
        }
        if (player.get(PlayerType.GREEN).getXKoordinate() == 0 && player.get(PlayerType.GREEN).getYKoordinate() == y)
        {
            placePlayerOutOfBounds(6, y, PlayerType.GREEN);
        }

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

        if (player.get(PlayerType.WHITE).getXKoordinate() == board.length - 1
            && player.get(PlayerType.WHITE).getYKoordinate() == y)
        {
            placePlayerOutOfBounds(0, y, PlayerType.WHITE);
        }

        if (player.get(PlayerType.BLACK).getXKoordinate() == board.length - 1
            && player.get(PlayerType.BLACK).getYKoordinate() == y)
        {
            placePlayerOutOfBounds(0, y, PlayerType.BLACK);
        }
        if (player.get(PlayerType.RED).getXKoordinate() == board.length - 1
            && player.get(PlayerType.RED).getYKoordinate() == y)
        {
            placePlayerOutOfBounds(0, y, PlayerType.RED);
        }
        if (player.get(PlayerType.GREEN).getXKoordinate() == board.length - 1
            && player.get(PlayerType.GREEN).getYKoordinate() == y)
        {
            placePlayerOutOfBounds(0, y, PlayerType.GREEN);
        }
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

    // Methoden, die das board betreffen
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

    public PlayerType getTurnHolder()
    {
        return turnHolder;
    }

    // legale züge
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

    public boolean isFinished()
    {

        if (playerCardsMap.get(turnHolder).getHiddenCards() == null) return true;
        turnHolder = null;
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

    public static class Position
    {

        final int x, y;

        public Position(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Position other = (Position) obj;
            if (x != other.x) return false;
            if (y != other.y) return false;
            return true;
        }

        public int getX()
        {
            return x;
        }

        public int getY()
        {
            return y;
        }

        public Position incX()
        {
            return new Position(x + 1, y);
        }

        public Position decX()
        {
            return new Position(x - 1, y);
        }

        public Position incY()
        {
            return new Position(x, y + 1);
        }

        public Position decY()
        {
            return new Position(x, y - 1);
        }

    }
}