package de.schelklingen2008.poker.client.view;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.schelklingen2008.poker.client.controller.Controller;
import de.schelklingen2008.poker.client.controller.GameChangeListener;

public class HistoryPanel extends JPanel implements GameChangeListener
{

    private Controller controller;

    public HistoryPanel(Controller controller)
    {
        this.controller = controller;
        paintHistoryPanel();
    }

    public void labels()
    {
        add(new JLabel("Text..."));
    }

    public void gameChanged()
    {
        removeAll();
        paintHistoryPanel();
    }

    private void paintHistoryPanel()
    {
        List<String> historyList = controller.getGameContext().getGameModel().getHistoryList();
        for (String s : historyList)
        {
            add(new JLabel(s));
        }
    }
}
