package de.schelklingen2008.dasverruecktelabyrinth.model;

import java.util.List;

import junit.framework.TestCase;
import de.schelklingen2008.dasverruecktelabyrinth.model.GameModel.Position;

public class GameModelTest extends TestCase
{

    public void testFindWay() throws Exception
    {
        GameModel model = new GameModel(new String[] { "Dick", "Doof" });
        Tile[][] board = model.getBoard();

        board[0][6] = Tile.HORIZONTAL;
        board[1][6] = Tile.CURVE4;
        board[1][5] = Tile.CURVE2;
        board[2][5] = Tile.CURVE4;
        board[2][4] = Tile.CROSS;
        board[1][4] = Tile.CURVE3;
        board[3][4] = Tile.CURVE4;
        board[3][3] = Tile.CURVE2;
        board[1][3] = Tile.HORIZONTAL;
        board[4][3] = Tile.HORIZONTAL;
        board[2][3] = Tile.HORIZONTAL;

        model.setBoard(board);

        List<Position> way;
        way = model.findWay(0, 6, 0, 6);
        assertNotNull(way);
        assertEquals(1, way.size());
        assertEquals(new Position(0, 6), way.get(0));

        way = model.findWay(0, 6, 1, 6);
        assertNotNull(way);
        assertEquals(2, way.size());
        assertEquals(new Position(0, 6), way.get(0));
        assertEquals(new Position(1, 6), way.get(1));

        way = model.findWay(0, 6, 2, 6);
        assertNull(way);

        way = model.findWay(0, 6, 4, 3);
        assertNotNull(way);
        assertEquals(8, way.size());
        assertEquals(new Position(0, 6), way.get(0));
        assertEquals(new Position(1, 6), way.get(1));
        assertEquals(new Position(1, 5), way.get(2));
        assertEquals(new Position(2, 5), way.get(3));
        assertEquals(new Position(2, 4), way.get(4));
        assertEquals(new Position(3, 4), way.get(5));
        assertEquals(new Position(3, 3), way.get(6));
        assertEquals(new Position(4, 3), way.get(7));

        way = model.findWay(-1, -1, 4, 3);
        assertNull(way);

    }

    public void testEinschieben() throws Exception
    {
        GameModel model = new GameModel(new String[] { "Dick", "Doof" });
        Tile[][] board = model.getBoard();

        Tile[] boardZeile = new Tile[7];

        for (int i = 0; i < boardZeile.length; i++)
        {
            boardZeile[i] = board[1][i];
        }

        Tile tempInsert = model.getInsertTile();

        model.insert(PushButton.SuedLinks);

        board = model.getBoard();

        assertEquals(tempInsert, board[1][6]);
        assertEquals(boardZeile[6], board[1][5]);
        assertEquals(boardZeile[5], board[1][4]);
        assertEquals(boardZeile[4], board[1][3]);
        assertEquals(boardZeile[3], board[1][2]);
        assertEquals(boardZeile[2], board[1][1]);
        assertEquals(boardZeile[1], board[1][0]);
        assertEquals(boardZeile[0], model.getInsertTile());

    }

    public void testPlacePlayer() throws Exception
    {
        GameModel model = new GameModel(new String[] { "Dick", "Doof" });
        Tile[][] board = model.getBoard();
        board = model.getBoard();

        board[0][0] = Tile.CROSS;
        board[1][0] = Tile.HORIZONTAL;
        board[2][0] = Tile.CURVE1;
        board[2][1] = Tile.VERTICAL;
        board[3][1] = Tile.CROSS;

        model.getPlayer(model.getTurnHolder()).setXKoordinate(0);
        model.getPlayer(model.getTurnHolder()).setYKoordinate(0);

        assertEquals(0, model.getPlayer(model.getTurnHolder()).getXKoordinate());
        assertEquals(0, model.getPlayer(model.getTurnHolder()).getYKoordinate());

        PlayerType turnHolder = model.getTurnHolder();
        model.setWalk(true);

        model.placePlayer(3, 1, model.getTurnHolder());

        assertEquals(3, model.getPlayer(turnHolder).getXKoordinate());
        assertEquals(1, model.getPlayer(turnHolder).getYKoordinate());

    }
}
