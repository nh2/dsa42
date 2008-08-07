package de.schelklingen2008.mmpoker.client.view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import de.schelklingen2008.mmpoker.client.controller.Controller;

public class PokerPanel extends JPanel {

    private Controller controller;

    public PokerPanel(Controller controller) {
        this.controller = controller;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(new BoardView(controller));
        add(Box.createVerticalStrut(5));
        add(new ButtonsPanel(controller));
    }

    public void gameChanged() {
        // TODO Auto-generated method stub

    }
}
