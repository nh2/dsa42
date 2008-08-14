package de.schelklingen2008.poker.model;

public class Pattern
{

    private int art;       // Art des Patterns, z.B. HighCard, Pärchen,...Straße, Flush...
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
                s = "Pärchen";
                break;
            case 2:
                s = "Doppelpärchen";
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

    public boolean greaterThan(Pattern other)
    {
        if (art > other.art) return true;
        if (art < other.art) return false;
        // wenn art gleich ist
        if (value1 > other.value1) return true;
        if (value1 < other.value1) return false;
        // wenn auch value1 gleich ist
        if (value2 > other.value2) return true;
        if (value2 < other.value2) return false;

        return false;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + art;
        result = prime * result + value1;
        result = prime * result + value2;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Pattern other = (Pattern) obj;
        if (art != other.art) return false;
        if (value1 != other.value1) return false;
        if (value2 != other.value2) return false;
        return true;
    }
}
