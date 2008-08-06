package de.schelklingen2008.poker.model;

public class Card
{

    private int number;
    private int suit;

    public String getValue()
    {
        if (number >= 0 && number <= 8) return "" + (number + 2);
        switch (number)
        {
            case 9:
                return "j";
            case 10:
                return "q";
            case 11:
                return "k";
            case 12:
                return "a";
        }
        return "Fehler";
    }

    public String getColor()
    {
        // Wandelt die in color in die entsprechende Farbe um
        return "";
    }

    public String getName()
    {
        return getColor() + " " + getValue();
    }

    public String getFileName()
    {
        return "";
    }
}
