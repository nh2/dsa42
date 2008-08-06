package de.schelklingen2008.risiko.model;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    private Country         island;
    private Country         irland;
    private Country         gb;
    private Country         norwegen;
    private Country         finnland;
    private Country         schweden;
    private Country         daenemark;
    private Country         russland;
    private Country         estland;
    private Country         lettland;
    private Country         litauen;
    private Country         weissrussland;
    private Country         ukraine;
    private Country         polen;
    private Country         italien;
    private Country         griechenland;
    private Country         jugoslawien;
    private Country         albanien;
    private Country         bulgarien;
    private Country         rumaenien;
    private Country         portugal;
    private Country         frankreich;
    private Country         spanien;
    private Country         benelux;
    private Country         deutschland;
    private Country         tschechien;
    private Country         schweiz;
    private Country         slowakei;
    private Country         oesterreich;
    private Country         ungarn;

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
        island = new Country("Island", 0, 0, 0, 0, 0, 0);
        irland = new Country("Irland", 1, 0, 0, 0, 0, 0);
        gb = new Country("Groﬂbritanien", 2, 0, 0, 0, 0, 0);
        norwegen = new Country("Norwegen", 3, 0, 0, 0, 0, 0);
        finnland = new Country("Finnland", 4, 0, 0, 0, 0, 0);
        schweden = new Country("Schweden", 5, 0, 0, 0, 0, 0);
        daenemark = new Country("D‰nemark", 6, 0, 0, 0, 0, 0);
        russland = new Country("Russland", 7, 0, 0, 0, 0, 0);
        estland = new Country("Estland", 8, 0, 0, 0, 0, 0);
        lettland = new Country("Lettland", 9, 0, 0, 0, 0, 0);
        litauen = new Country("Litauen", 10, 0, 0, 0, 0, 0);
        weissrussland = new Country("Weiﬂrussland", 11, 0, 0, 0, 0, 0);
        ukraine = new Country("Ukraine", 12, 0, 0, 0, 0, 0);
        polen = new Country("Polen", 13, 0, 0, 0, 0, 0);
        italien = new Country("Italien", 14, 0, 0, 0, 0, 0);
        griechenland = new Country("Griechenland", 15, 0, 0, 0, 0, 0);
        jugoslawien = new Country("Jugoslawien", 16, 0, 0, 0, 0, 0);
        albanien = new Country("Albanien", 17, 0, 0, 0, 0, 0);
        bulgarien = new Country("Bulgarien", 18, 0, 0, 0, 0, 0);
        rumaenien = new Country("Rum‰nien", 19, 0, 0, 0, 0, 0);
        portugal = new Country("Portugal", 20, 0, 0, 0, 0, 0);
        frankreich = new Country("Frankreich", 21, 0, 0, 0, 0, 0);
        spanien = new Country("Spanien", 22, 0, 0, 0, 0, 0);
        benelux = new Country("BeNeLux", 23, 0, 0, 0, 0, 0);
        deutschland = new Country("Deutschland", 24, 0, 0, 0, 0, 0);
        tschechien = new Country("Tschechien", 25, 0, 0, 0, 0, 0);
        schweiz = new Country("Schweiz", 26, 0, 0, 0, 0, 0);
        slowakei = new Country("Slowakei", 27, 0, 0, 0, 0, 0);
        oesterreich = new Country("÷sterreich", 28, 0, 0, 0, 0, 0);
        ungarn = new Country("Ungarn", 29, 0, 0, 0, 0, 0);

    }

    public void setTurnholder()
    {
        turnholder = p[player++];
    }

}