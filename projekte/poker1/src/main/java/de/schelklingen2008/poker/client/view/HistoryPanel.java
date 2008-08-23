package de.schelklingen2008.poker.client.view;

import java.awt.Color;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.schelklingen2008.poker.client.Constants;
import de.schelklingen2008.poker.client.controller.Controller;
import de.schelklingen2008.poker.client.controller.GameChangeListener;
import de.schelklingen2008.poker.model.GameModel;

public class HistoryPanel extends JPanel implements GameChangeListener
{

    private Controller controller;

    public HistoryPanel(Controller controller)
    {
        this.controller = controller;
        controller.addChangeListener(this);
        paintHistoryPanel();
    }

    public void gameChanged()
    {
        paintHistoryPanel();
    }

    private void paintHistoryPanel()
    {
        removeAll();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        GameModel model = controller.getGameContext().getGameModel();
        if (model == null) return;
        List<String> historyList = model.getHistoryList();

        for (String s : historyList)
        {
            if (historyList.size() >= 25) historyList.remove(0);
            add(createLabel(s));
        }
        if (historyList.size() < 25)
        {
            for (int i = historyList.size(); i < 25; i++)
            {
                add(createLabel(" "));
            }
        }
        revalidate();
        repaint();
    }

    private JLabel createLabel(String text)
    {
        JLabel label = new JLabel(text);
        label.setOpaque(false);
        label.setForeground(Color.WHITE);
        label.setBackground(Constants.BACK_GREEN);
        return label;
    }
}
