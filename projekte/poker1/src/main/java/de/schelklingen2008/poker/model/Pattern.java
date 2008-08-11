package de.schelklingen2008.poker.model;

public class Pattern
{

    private int art; // Art des Patterns, z.B. HighCard, P‰rchen,...Straﬂe, Flush...

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
            default:
                s = "";
        }
        return s;
    }

}
