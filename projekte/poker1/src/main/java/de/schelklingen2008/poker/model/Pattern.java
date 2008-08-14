package de.schelklingen2008.poker.model;

public class Pattern
{

    private int art;       // Art des Patterns, z.B. HighCard, P‰rchen,...Straﬂe, Flush...
    private int value1 = 0;
    private int value2 = 0;

    public Pattern(int art, int value1, int value2)
    {
        this.art = art;
        this.value1 = value1;
        this.value2 = value2;
    }

    public String artToSTring()
    {
        String s;
        switch (art)
        {
            case 0:
                s = "HighCard";
                break;
            case 1:
                s = "P‰rchen";
                break;
            case 2:
                s = "Doppelp‰rchen";
                break;
            case 3:
                s = "Drilling";
                break;
            case 4:
                s = "Straight";
                break;
            case 5:
                s = "Flush";
                break;
            case 6:
                s = "Full House";
                break;
            case 7:
                s = "Vierling";
                break;
            case 8:
                s = "Straight Flush";
                break;
            case 9:
                s = "Royal Flush";
            default:
                s = "Falsche Zahl";
        }
        return s;
    }

}
