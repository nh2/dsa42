package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.samskivert.swing.GroupLayout;
import com.samskivert.swing.MultiLineLabel;
import com.threerings.crowd.client.PlaceView;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.toybox.client.ChatPanel;
import com.threerings.toybox.client.ToyBoxUI;

import de.schelklingen2008.dasverruecktelabyrinth.client.Constants;
import de.schelklingen2008.dasverruecktelabyrinth.client.controller.Controller;
import de.schelklingen2008.dasverruecktelabyrinth.client.model.GameContext;
import de.schelklingen2008.dasverruecktelabyrinth.model.GameModel;

/**
 * Contains the primary client interface for the game.
 */
public class GamePanel extends JPanel implements PlaceView
{

    private Controller controller;

    private class ActionListenerImplementation implements ActionListener
    {

        private Controller controller;

        private ActionListenerImplementation(Controller controller)
        {
            this.controller = controller;
        }

        public void actionPerformed(ActionEvent e)
        {
            controller.leaveButtonClicked();
        }
    }

    /**
     * Creates a TestGame panel and its associated interface components.
     */
    public GamePanel(Controller controller)
    {

        this.controller = controller;

        LabyPlusPlayerPanel labyPlusPP = new LabyPlusPlayerPanel(controller);

        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());
        setBackground(new Color(0x6699CC));

        // JPanel box = GroupLayout.makeHBox();
        // box.setOpaque(false);
        // box.add(new LabyrinthPanel(controller));
        // add(box, BorderLayout.CENTER);

        // create a side panel to hold our chat and other extra interfaces
        JPanel sidePanel = GroupLayout.makeVStretchBox(5);
        sidePanel.setOpaque(false);

        // add a big fat label
        MultiLineLabel vlabel = new MultiLineLabel(controller.getMessage(Constants.MSG_TITLE));
        vlabel.setAntiAliased(true);
        vlabel.setFont(ToyBoxUI.fancyFont);
        sidePanel.add(vlabel, GroupLayout.FIXED);

        // add the player's name
        JLabel playersName = new JLabel("  You Are: " + getGameContext().getMyName());
        playersName.setBackground(Color.WHITE);
        playersName.setBorder(BorderFactory.createLineBorder(Color.black));
        sidePanel.add(playersName, GroupLayout.FIXED);
        playersName.setOpaque(true);

        // add a standard turn display
        TurnPanel turnDisplay = new TurnPanel(controller);
        turnDisplay.setOpaque(false);
        sidePanel.add(turnDisplay, GroupLayout.FIXED);

        // add a chat box
        sidePanel.add(new ChatPanel(controller.getToyBoxContext()));

        // add a "back to lobby" button
        JButton back = new JButton();
        back.addActionListener(new ActionListenerImplementation(controller));
        back.setText(controller.getMessage(Constants.MSG_BACK_TO_LOBBY));
        sidePanel.add(back, GroupLayout.FIXED);

        // add our side panel to the main display
        add(sidePanel, BorderLayout.EAST);

        add(labyPlusPP, BorderLayout.WEST);

    }

    /** The interface PlaceView is only implemented as a marker interface. Nothing to do here. */
    public void didLeavePlace(PlaceObject placeObject)
    {
    }

    /** The interface PlaceView is only implemented as a marker interface. Nothing to do here. */
    public void willEnterPlace(PlaceObject placeObject)
    {
    }

    private GameContext getGameContext()
    {
        return controller.getGameContext();
    }

    private GameModel getGameModel()
    {
        return getGameContext().getGameModel();
    }

}
