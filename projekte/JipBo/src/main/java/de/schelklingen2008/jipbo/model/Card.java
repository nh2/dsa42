package de.schelklingen2008.jipbo.model;

public class Card
{

    private int mN;

    public void main(String[] args)
    {
        mN = Integer.valueOf(args[0]);
        if (mN < 0 && mN > 12) throw new IllegalArgumentException("The number must be between 0 and 12 ");

    }

    public int getNumber()
    {
        return mN;

    }
}
