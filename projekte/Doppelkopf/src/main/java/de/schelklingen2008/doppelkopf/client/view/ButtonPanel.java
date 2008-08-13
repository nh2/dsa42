package de.schelklingen2008.doppelkopf.client.view;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.schelklingen2008.doppelkopf.client.controller.Controller;
import de.schelklingen2008.doppelkopf.client.controller.GameChangeListener;
import de.schelklingen2008.doppelkopf.model.GameModel;

public class ButtonPanel extends JPanel implements GameChangeListener
{

    GameModel          spiel;
    private Controller controller;
    JButton            hochzeitButton;
    JTextArea          nachrichtenBox = new JTextArea();

    public ButtonPanel(Controller controller)
    {

        this.controller = controller;
        controller.addChangeListener(this);

        hochzeitButton = new JButton("Der erste Fremde");
        add(hochzeitButton);
        add(nachrichtenBox);

    }

    public void gameChanged()
    {
        spiel = controller.getGameContext().getGameModel();

        String inhalt = "";
        List<String> nachrichten = spiel.getNachrichten();
        for (String s : nachrichten)
        {
            inhalt += s;
            inhalt += "\n";
        }
        nachrichtenBox.setText(inhalt);
    }

}
