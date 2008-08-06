package de.schelklingen2008.risiko.model;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    private Country[]       c = new Country[30];
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
        c[0] = new Country("Island", 0, 90, 160, 0, 0, 0);
        c[1] = new Country("Irland", 1, 128, 498, 0, 0, 0);
        c[2] = new Country("Groﬂbritanien", 2, 210, 518, 0, 0, 0);
        c[3] = new Country("Norwegen", 3, 378, 307, 0, 0, 0);
        c[4] = new Country("Finnland", 4, 578, 253, 0, 0, 0);
        c[5] = new Country("Schweden", 5, 467, 346, 0, 0, 0);
        c[6] = new Country("D‰nemark", 6, 383, 439, 0, 0, 0);
        c[7] = new Country("Russland", 7, 822, 336, 0, 0, 0);
        c[8] = new Country("Estland", 8, 607, 351, 0, 0, 0);
        c[9] = new Country("Lettland", 9, 598, 403, 0, 0, 0);
        c[10] = new Country("Litauen", 10, 595, 444, 0, 0, 0);
        c[11] = new Country("Weiﬂrussland", 11, 660, 485, 0, 0, 0);
        c[12] = new Country("Ukraine", 12, 710, 562, 0, 0, 0);
        c[13] = new Country("Polen", 13, 527, 528, 0, 0, 0);
        c[14] = new Country("Italien", 14, 400, 740, 0, 0, 0);
        c[15] = new Country("Griechenland", 15, 652, 848, 0, 0, 0);
        c[16] = new Country("Jugoslawien", 16, 546, 732, 0, 0, 0);
        c[17] = new Country("Albanien", 17, 605, 819, 0, 0, 0);
        c[18] = new Country("Bulgarien", 18, 652, 672, 0, 0, 0);
        c[19] = new Country("Rum‰nien", 19, 688, 758, 0, 0, 0);
        c[20] = new Country("Portugal", 20, 25, 803, 0, 0, 0);
        c[21] = new Country("Frankreich", 21, 247, 674, 0, 0, 0);
        c[22] = new Country("Spanien", 22, 113, 826, 0, 0, 0);
        c[23] = new Country("BeNeLux", 23, 313, 549, 0, 0, 0);
        c[24] = new Country("Deutschland", 24, 380, 564, 0, 0, 0);
        c[25] = new Country("Tschechien", 25, 480, 610, 0, 0, 0);
        c[26] = new Country("Schweiz", 26, 367, 685, 0, 0, 0);
        c[27] = new Country("Slowakei", 27, 557, 633, 0, 0, 0);
        c[28] = new Country("÷sterreich", 28, 465, 675, 0, 0, 0);
        c[29] = new Country("Ungarn", 29, 550, 673, 0, 0, 0);

    }

    public void setTurnholder()
    {
        turnholder = p[player++];
    }

}