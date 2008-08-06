package de.schelklingen2008.doppelkopf.client.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.schelklingen2008.doppelkopf.client.controller.GameChangeListener;

public class ButtonPanel extends JPanel implements GameChangeListener
{

    JButton hochzeitButton;

    public ButtonPanel()
    {
        hochzeitButton = new JButton("Der erste Fremde");
        add(hochzeitButton);
    }

    public void gameChanged()
    {
    }

}
