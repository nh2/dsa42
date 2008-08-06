package de.schelklingen2008.risiko.model;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    Country         island;
    Country         irland;
    Country         gb;
    Country         norwegen;
    Country         finnland;
    Country         schweden;
    Country         daenemark;
    Country         russland;
    Country         estland;
    Country         lettland;
    Country         litauen;
    Country         weissrussland;
    Country         ukraine;
    Country         polen;
    Country         italien;
    Country         griechenland;
    Country         jugoslawien;
    Country         albanien;
    Country         bulgarien;
    Country         rumaenien;
    Country         portugal;
    Country         frankreich;
    Country         spanien;
    Country         benelux;
    Country         deutschland;
    Country         tschechien;
    Country         schweiz;
    Country         slowakei;
    Country         oesterreich;
    Country         ungarn;

    Country[]       c;
    static Player[] p;

    public GameModel()
    {
        initCountrys();

    }

    public Player valueOf(int playerIndex)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Country getCountry(int i)
    {
        return c[i];
    }

    private void initCountrys()
    {
        island = new Country("Island", 0, 0, 0, null);
        irland = new Country("Irland", 1, 0, 0, null);
        gb = new Country("Groﬂbritanien", 2, 0, 0, null);
        norwegen = new Country("Norwegen", 3, 0, 0, null);
        finnland = new Country("Finnland", 4, 0, 0, null);
        schweden = new Country("Schweden", 5, 0, 0, null);
        daenemark = new Country("D‰nemark", 6, 0, 0, null);
        russland = new Country("Russland", 7, 0, 0, null);
        estland = new Country("Estland", 8, 0, 0, null);
        lettland = new Country("Lettland", 9, 0, 0, null);
        litauen = new Country("Litauen", 10, 0, 0, null);
        weissrussland = new Country("Weiﬂrussland", 11, 0, 0, null);
        ukraine = new Country("Ukraine", 12, 0, 0, null);
        polen = new Country("Polen", 13, 0, 0, null);
        italien = new Country("Italien", 14, 0, 0, null);
        griechenland = new Country("Griechenland", 15, 0, 0, null);
        jugoslawien = new Country("Jugoslawien", 16, 0, 0, null);
        albanien = new Country("Albanien", 17, 0, 0, null);
        bulgarien = new Country("Bulgarien", 18, 0, 0, null);
        rumaenien = new Country("Rum‰nien", 19, 0, 0, null);
        portugal = new Country("Portugal", 20, 0, 0, null);
        frankreich = new Country("Frankreich", 21, 0, 0, null);
        spanien = new Country("Spanien", 22, 0, 0, null);
        benelux = new Country("BeNeLux", 23, 0, 0, null);
        deutschland = new Country("Deutschland", 24, 0, 0, null);
        tschechien = new Country("Tschechien", 25, 0, 0, null);
        schweiz = new Country("Schweiz", 26, 0, 0, null);
        slowakei = new Country("Slowakei", 27, 0, 0, null);
        oesterreich = new Country("÷sterreich", 28, 0, 0, null);
        ungarn = new Country("Ungarn", 29, 0, 0, null);

    }

}