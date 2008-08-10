package de.schelklingen2008.jipbo.model;

import java.io.Serializable;

public class Card implements Serializable
{

    private int mN;

    public Card(int pN)
    {
        mN = pN;
    }

    public int getNumber()
    {
        return mN;

    }

    public void setNumber(int pN)
    {
        mN = pN;
    }
}
