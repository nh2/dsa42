package de.schelklingen2008.risiko.model;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    private Country[]       c;
    private static Player[] p;
    private Player          turnholder;
    private int             player;

    public GameModel()
    {
        initCountrys();
        player = 0;

    }

    public Player valueOf(int playerIndex)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Country[] getCountryArray()
    {
        return c;
    }

    public Country getCountry(int i)
    {
        return c[i];
    }

    public Player getTurnholder()
    {
        return turnholder;
    }

    private void initCountrys()
    {
        c[0] = new Country("Island", 0, 0, 0, 0, 0, 0);
        c[1] = new Country("Irland", 1, 0, 0, 0, 0, 0);
        c[2] = new Country("Groﬂbritanien", 2, 0, 0, 0, 0, 0);
        c[3] = new Country("Norwegen", 3, 0, 0, 0, 0, 0);
        c[4] = new Country("Finnland", 4, 0, 0, 0, 0, 0);
        c[5] = new Country("Schweden", 5, 0, 0, 0, 0, 0);
        c[6] = new Country("D‰nemark", 6, 0, 0, 0, 0, 0);
        c[7] = new Country("Russland", 7, 0, 0, 0, 0, 0);
        c[8] = new Country("Estland", 8, 0, 0, 0, 0, 0);
        c[9] = new Country("Lettland", 9, 0, 0, 0, 0, 0);
        c[10] = new Country("Litauen", 10, 0, 0, 0, 0, 0);
        c[11] = new Country("Weiﬂrussland", 11, 0, 0, 0, 0, 0);
        c[12] = new Country("Ukraine", 12, 0, 0, 0, 0, 0);
        c[13] = new Country("Polen", 13, 0, 0, 0, 0, 0);
        c[14] = new Country("Italien", 14, 0, 0, 0, 0, 0);
        c[15] = new Country("Griechenland", 15, 0, 0, 0, 0, 0);
        c[16] = new Country("Jugoslawien", 16, 0, 0, 0, 0, 0);
        c[17] = new Country("Albanien", 17, 0, 0, 0, 0, 0);
        c[18] = new Country("Bulgarien", 18, 0, 0, 0, 0, 0);
        c[19] = new Country("Rum‰nien", 19, 0, 0, 0, 0, 0);
        c[20] = new Country("Portugal", 20, 0, 0, 0, 0, 0);
        c[21] = new Country("Frankreich", 21, 0, 0, 0, 0, 0);
        c[22] = new Country("Spanien", 22, 0, 0, 0, 0, 0);
        c[23] = new Country("BeNeLux", 23, 0, 0, 0, 0, 0);
        c[24] = new Country("Deutschland", 24, 0, 0, 0, 0, 0);
        c[25] = new Country("Tschechien", 25, 0, 0, 0, 0, 0);
        c[26] = new Country("Schweiz", 26, 0, 0, 0, 0, 0);
        c[27] = new Country("Slowakei", 27, 0, 0, 0, 0, 0);
        c[28] = new Country("÷sterreich", 28, 0, 0, 0, 0, 0);
        c[29] = new Country("Ungarn", 29, 0, 0, 0, 0, 0);

    }

    public void setTurnholder()
    {
        turnholder = p[player++];
    }

}