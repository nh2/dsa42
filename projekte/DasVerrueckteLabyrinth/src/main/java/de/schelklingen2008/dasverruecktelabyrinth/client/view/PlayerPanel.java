package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PlayerPanel extends JPanel
{

    public PlayerPanel()
    {
        setLayout(new BorderLayout());

        JPanel drehButtons = new JPanel();

        drehButtons.setLayout(new BoxLayout(drehButtons, BoxLayout.PAGE_AXIS));
        drehButtons.add(new Button("rechts drehen"));
        drehButtons.add(new Button("links drehen"));

        JPanel insertTile = new JPanel();

        JPanel playerCards = new JPanel();

        insertTile.setLayout(new BorderLayout());

    }
}
