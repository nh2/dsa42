package de.schelklingen2008.jipbo.client.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BoardPanel extends JPanel
{

    private int[] mN;

    public BoardPanel(int[] pN)
    {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mN = pN;
        for (int i = 0; i < mN.length; i++)
        {
            if (i == 4)
            {
                add(new CardPanel(mN[i], true, true));
            }
            else
            {
                add(new CardPanel(mN[i], false, false));
            }
        }
    }

    public void setValue(int[] pN)
    {
        mN = pN;
        repaint();
    }
}
