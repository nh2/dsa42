package de.schelklingen2008.dasverruecktelabyrinth.model;

import junit.framework.TestCase;

public class TileTest extends TestCase
{

    public void testTurning() throws Exception
    {
        Tile tile = new Tile(true, false, false, true, null);
        assertTrue(tile.isCurve4());
        tile.turnRight();
        assertTrue(tile.isCurve3());
        tile.turnLeft();
        assertTrue(tile.isCurve4());
        tile.turnLeft();
        assertTrue(tile.isCurve1());
        tile.turnLeft();
        assertTrue(tile.isCurve2());
        tile.turnLeft();
        assertTrue(tile.isCurve3());
        tile.turnLeft();
        assertTrue(tile.isCurve4());

        System.out.println((-1 + 7) % 7);
    }
}
