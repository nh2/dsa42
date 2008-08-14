package de.schelklingen2008.poker.client.view;

import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.schelklingen2008.poker.client.controller.Controller;

public class HistoryPanel extends JPanel
{

    public HistoryPanel(Controller controller)
    {

        List<String> historyList = controller.getGameContext().getGameModel().getHistoryList();
        for (Iterator iterator = historyList.iterator(); iterator.hasNext();)
        {
            String s = (String) iterator.next();
            add(new JLabel(s));
        }
    }

    public void labels()
    {
        add(new JLabel("Text..."));
    }
}
