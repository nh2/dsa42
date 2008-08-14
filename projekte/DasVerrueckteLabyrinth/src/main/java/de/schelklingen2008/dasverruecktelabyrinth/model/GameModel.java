package de.schelklingen2008.dasverruecktelabyrinth.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import java.util.logging.Logger;

import de.schelklingen2008.util.LoggerFactory;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel implements Serializable
{

    private static final PlayerType[]    TYPES          = new PlayerType[] { PlayerType.WHITE, PlayerType.BLACK,
            PlayerType.RED, PlayerType.GREEN           };

    public static final int              SIZE           = 7;
    public static final PlayerType       PLAYER_START   = PlayerType.WHITE;

    private Tile[][]                     board;                                                    // Spielbrett
    private PlayerType                   turnHolder;                                               // Wer ist
    // dran
    private boolean                      walk           = false;                                   // false =
    // Phase
    // 1

    // true = Phase2
    private Tile                         insert         = new Tile(true, true, false, false, null); // einschiebbare
    // Spielfeldkarte

    private Map<PlayerType, Player>      players        = new HashMap<PlayerType, Player>();
    private Map<PlayerType, PlayerCards> playerCardsMap = new HashMap<PlayerType, PlayerCards>();

    private static final Random          RAND           = new Random();

    private static final Logger          sLogger        = LoggerFactory.create();

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
            players.put(TYPES[i], new Player(TYPES[i], names[i], x, y)); // TODO brauch ich placePlayerStart
            // noch?
        }

        setCorners(board);

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
        List<TreasureCard> treasureCards = Arrays.asList(TreasureCard.values());
        Collections.shuffle(treasureCards);

        int noOfPlayers = getPlayers().size();

        List<Player> players = new ArrayList<Player>(getPlayers());
        for (int i = 0; i < treasureCards.size(); i++)
        {
            TreasureCard tc = treasureCards.get(i);
            Player player = players.get(i % noOfPlayers);
            PlayerType playerType = player.getPlayerType();
            PlayerCards playerCards = playerCardsMap.get(playerType);
            if (playerCards == null)
            {
                playerCards = new PlayerCards();
                playerCardsMap.put(playerType, playerCards);
            }
            playerCards.getHiddenCards().add(tc);
        }
    }

    public void setCorners(Tile[][] pBoard)
    {
        boolean rechtsOben = false;
        boolean linksOben = false;
        boolean rechtsUnten = false;
        boolean linksUnten = false;

        for (int x = 0; x < pBoard.length; x++)
        {
            for (int y = 0; y < pBoard.length; y++)
            {
                if (linksOben == false)
                {
                    if (pBoard[x][y].isCurve2())
                    {
                        Tile bufferTile = pBoard[0][0];
                        pBoard[0][0] = pBoard[x][y];
                        pBoard[x][y] = bufferTile;
                        linksOben = true;
                    }
                }
                if (rechtsOben == false)
                {
                    if (pBoard[x][y].isCurve1())
                    {
                        Tile bufferTile = pBoard[6][0];
                        pBoard[6][0] = pBoard[x][y];
                        pBoard[x][y] = bufferTile;
                        rechtsOben = true;
                    }
                }
                if (rechtsUnten == false)
                {
                    if (pBoard[x][y].isCurve4())
                    {
                        Tile bufferTile = pBoard[6][6];
                        pBoard[6][6] = pBoard[x][y];
                        pBoard[x][y] = bufferTile;
                        rechtsUnten = true;
                    }
                }
                if (linksUnten == false)
                {
                    if (pBoard[x][y].isCurve3())
                    {
                        Tile bufferTile = pBoard[0][6];
                        pBoard[0][6] = pBoard[x][y];
                        pBoard[x][y] = bufferTile;
                        linksUnten = true;
                    }
                }

            }
        }
    }

    public Player getWinner()
    {
        if (!isFinished()) return null;

        List<TreasureCard> hiddenWhite = playerCardsMap.get(PlayerType.WHITE).getHiddenCards();
        List<TreasureCard> hiddenBlack = playerCardsMap.get(PlayerType.BLACK).getHiddenCards();
        List<TreasureCard> hiddenRed = playerCardsMap.get(PlayerType.RED).getHiddenCards();
        List<TreasureCard> hiddenGreen = playerCardsMap.get(PlayerType.GREEN).getHiddenCards();

        if (hiddenWhite == null) return players.get(PlayerType.WHITE);
        if (hiddenBlack == null) return players.get(PlayerType.BLACK);
        if (hiddenRed == null) return players.get(PlayerType.RED);
        if (hiddenGreen == null) return players.get(PlayerType.GREEN);

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

        PlayerCards playerCards = playerCardsMap.get(pPlayer.getPlayerType());

        if (board[x][y].getTC() != playerCards.getFirstHidden()) return;

        playerCards.moveFirstHiddenToOpen();

    }

    // setzt den Turnholder im normalen Spiel
    public void placePlayer(int x, int y, PlayerType pPlayerType)
    {
        if (walk)
        {
            if (legalMovePossible() == true)
            {
                Player pPlayer = getPlayer(pPlayerType);
                if (isLegalMove(x, y, pPlayer))
                {
                    pPlayer.setXKoordinate(x);
                    pPlayer.setYKoordinate(y);
                    placedOnSearchCard(pPlayer);
                    walk = false;
                }
                if (walk == false)
                {
                    sLogger.fine("lolz pls ruf mich auf");
                    changeTurnHolder();
                }
            }
            else
                changeTurnHolder();

        }

    }

    // setzt den herausgeschobenen Player -egal ob turnHolder oder nicht- auf der anderen Seite wieder ins
    // Spiel
    public void placePlayerOutOfBounds(int x, int y, PlayerType pPlayerType)
    {
        Player temp = getPlayer(pPlayerType);

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

    public List<Position> findWay(Position start, Position end, Stack<Position> last)
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
            && board[right.getX()][right.getY()].getLeft()) result = findWay(right, end, last);
        if (result == null
            && isInBounds(left.getX(), left.getY())
            && !last.contains(left)
            && board[start.getX()][start.getY()].getLeft()
            && board[left.getX()][left.getY()].getRight()) result = findWay(left, end, last);
        if (result == null
            && isInBounds(up.getX(), up.getY())
            && !last.contains(up)
            && board[start.getX()][start.getY()].getUp()
            && board[up.getX()][up.getY()].getDown()) result = findWay(up, end, last);
        if (result == null
            && isInBounds(down.getX(), down.getY())
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

    public boolean legalMovePossible()
    {
        Player player = players.get(turnHolder);
        int playerX = player.getXKoordinate();
        int playerY = player.getYKoordinate();

        boolean possible = false;

        if (findWay(playerX, playerY, playerX + 1, playerY) != null) possible = true;
        if (findWay(playerX, playerY, playerX - 1, playerY) != null) possible = true;
        if (findWay(playerX, playerY, playerX, playerY + 1) != null) possible = true;
        if (findWay(playerX, playerY, playerX, playerY - 1) != null) possible = true;

        return possible;
    }

    private boolean isTurnHolder(Player player2)
    {
        boolean temp = false;
        if (player2.getPlayerType() == turnHolder) temp = true;

        return temp;
    }

    public void changeTurnHolder()
    {
        if (getPlayers().size() == 2)
        {
            if (turnHolder == PlayerType.WHITE)
                turnHolder = PlayerType.BLACK;
            else if (turnHolder == PlayerType.BLACK) turnHolder = PlayerType.WHITE;
        }
        if (getPlayers().size() == 3)
        {
            if (turnHolder == PlayerType.WHITE)
                turnHolder = PlayerType.BLACK;
            else if (turnHolder == PlayerType.BLACK)
                turnHolder = PlayerType.RED;
            else if (turnHolder == PlayerType.RED) turnHolder = PlayerType.WHITE;
        }
        if (getPlayers().size() == 4)
        {

            if (turnHolder == PlayerType.WHITE)
                turnHolder = PlayerType.BLACK;
            else if (turnHolder == PlayerType.BLACK)
                turnHolder = PlayerType.RED;
            else if (turnHolder == PlayerType.RED)
                turnHolder = PlayerType.GREEN;
            else if (turnHolder == PlayerType.GREEN) turnHolder = PlayerType.WHITE;
        }

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
            if (pPushButton == PushButton.SuedLinks)
            {
                einschiebenSued(1, 6);
                walk = true;
            }
            if (pPushButton == PushButton.SuedMitte)
            {
                einschiebenSued(3, 6);
                walk = true;
            }
            if (pPushButton == PushButton.SuedRechts)
            {
                einschiebenSued(5, 6);
                walk = true;
            }
            if (pPushButton == PushButton.NordLinks)
            {
                einschiebenNord(1, 0);
                walk = true;
            }
            if (pPushButton == PushButton.NordMitte)
            {
                einschiebenNord(3, 0);
                walk = true;
                sLogger.fine("TurnHolder: " + getTurnHolder().toString());
            }
            if (pPushButton == PushButton.NordRechts)
            {
                einschiebenNord(5, 0);
                walk = true;
            }
            if (pPushButton == PushButton.OstUnten)
            {
                einschiebenOst(6, 5);
                walk = true;
            }
            if (pPushButton == PushButton.OstMitte)
            {
                einschiebenOst(6, 3);
                walk = true;
            }
            if (pPushButton == PushButton.OstOben)
            {
                einschiebenOst(6, 1);
                walk = true;
            }
            if (pPushButton == PushButton.WestUnten)
            {
                einschiebenWest(0, 5);
                walk = true;
            }
            if (pPushButton == PushButton.WestMitte)
            {
                einschiebenWest(0, 3);
                walk = true;
            }
            if (pPushButton == PushButton.WestOben)
            {
                einschiebenWest(0, 1);
                walk = true;
            }

        }

    }

    private void einschiebenNord(int x, int y)
    {
        Tile topTile = insert;
        insert = board[x][board.length - 1];
        for (int i = board.length - 1; i >= 1; i--)
        {
            board[x][i] = board[x][i - 1];

        }
        board[x][0] = topTile;

        for (PlayerType pt : PlayerType.values())
        {
            Player player = getPlayer(pt);
            if (player == null) continue;

            if (player.getXKoordinate() == x) player.setYKoordinate((player.getYKoordinate() + 1) % 7);
        }

    }

    private void einschiebenSued(int x, int y)
    {
        Tile tileBottom = insert;
        insert = board[x][0];
        for (int i = 0; i < board.length - 1; i++)
        {
            board[x][i] = board[x][i + 1];

        }
        board[x][(board.length - 1)] = tileBottom;

        for (PlayerType pt : PlayerType.values())
        {
            Player player = getPlayer(pt);
            if (player == null) continue;

            if (player.getXKoordinate() == x) player.setYKoordinate((player.getYKoordinate() - 1 + 7) % 7);
        }
    }

    private void einschiebenOst(int x, int y)
    {

        Tile tileEast = insert;
        insert = board[0][y];
        for (int i = 0; i < board.length - 1; i++)
        {
            board[i][y] = board[i + 1][y];

        }
        board[(board.length - 1)][y] = tileEast;

        for (PlayerType pt : PlayerType.values())
        {
            Player player = getPlayer(pt);
            if (player == null) continue;

            if (player.getYKoordinate() == y) player.setXKoordinate((player.getXKoordinate() - 1 + 7) % 7);
        }

    }

    private void einschiebenWest(int x, int y)
    {
        Tile tileWest = insert;
        insert = board[board.length - 1][y];
        for (int i = board.length - 1; i >= 1; i--)
        {
            board[i][y] = board[i - 1][y];
        }
        board[0][y] = tileWest;

        for (PlayerType pt : PlayerType.values())
        {
            Player player = getPlayer(pt);
            if (player == null) continue;

            if (player.getYKoordinate() == y) player.setXKoordinate((player.getXKoordinate() + 1) % 7);
        }
    }

    public boolean hasLegalMoves()
    {
        for (int y = 0; y < SIZE; y++)
            for (int x = 0; x < SIZE; x++)
                if (isLegalMove(x, y, players.get(getTurnHolder()))) return true;
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
        sLogger.fine("turnHolder: " + turnHolder);

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
                result[x][y] = isLegalMove(x, y, getPlayer(player));
        return result;
    }

    public boolean isFinished()
    {

        if (playerCardsMap.get(turnHolder).getHiddenCards() == null)
        {
            turnHolder = null;
            return true;
        }

        return false;
    }

    public Tile getInsertTile()
    {
        return insert;
    }

    public String getName(PlayerType playerType)
    {
        Player p = getPlayer(playerType);
        if (p == null) return null;
        return p.getName();
    }

    public Collection<Player> getPlayers()
    {
        return players.values();
    }

    public Player getPlayer(String name)
    {
        if (name == null) return null;
        for (Player p : players.values())
            if (name.equals(p.getName())) return p;
        return null;
    }

    public Player getPlayer(PlayerType pt)
    {
        return players.get(pt);
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

    @Override
    public String toString()
    {
        String result = "";
        for (int y = 0; y < board.length; y++)
        {
            for (int x = 0; x < board.length; x++)
            {
                result += board[x][y];
            }
            result += "\n";
        }
        return result;
    }

    public void setWalk(boolean p)
    {
        walk = p;
    }
}