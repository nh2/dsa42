package de.schelklingen2008.doppelkopf.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.schelklingen2008.doppelkopf.client.controller.Controller;
import de.schelklingen2008.doppelkopf.client.controller.GameChangeListener;
import de.schelklingen2008.doppelkopf.model.GameModel;
import de.schelklingen2008.doppelkopf.model.Spieler;

public class ButtonPanel extends JPanel implements GameChangeListener
{

    GameModel          spiel;
    private Controller controller;
    JButton            hochzeitButton;
    JTextArea          nachrichtenBox = new JTextArea();
    Spieler            ich;

    public ButtonPanel(Controller pController)
    {

        controller = pController;
        pController.addChangeListener(this);

        hochzeitButton = new JButton("Der erste Fremde");
        hochzeitButton.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                controller.ersterFremderButtonClicked();
            }
        });
        add(nachrichtenBox);

    }

    public void gameChanged()
    {
        spiel = controller.getGameContext().getGameModel();
        ich = controller.getGameContext().getMyPlayer();

        // Nachrichtenbox
        String inhalt = "";
        List<String> nachrichten = spiel.getNachrichten();
        for (String s : nachrichten)
        {
            inhalt += s;
            inhalt += "\n";
        }
        nachrichtenBox.setText(inhalt);

        // Hochzeitsbutton
        if (spiel.getTisch().getHochzeitSpieler() == ich) add(hochzeitButton);
    }

}
