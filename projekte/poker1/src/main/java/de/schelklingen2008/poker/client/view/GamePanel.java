package de.schelklingen2008.poker.client.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.samskivert.swing.GroupLayout;
import com.samskivert.swing.MultiLineLabel;
import com.threerings.crowd.client.PlaceView;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.toybox.client.ChatPanel;
import com.threerings.toybox.client.ToyBoxUI;

import de.schelklingen2008.poker.client.Constants;
import de.schelklingen2008.poker.client.controller.Controller;

/**
 * Contains the primary client interface for the game.
 */
public class GamePanel extends JPanel implements PlaceView
{

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

        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());
        setBackground(Constants.BACK_GREEN);

        JPanel box = GroupLayout.makeHBox();
        box.setOpaque(false);
        box.add(new BoardView(controller));
        add(box, BorderLayout.CENTER);

        // create a side panel to hold our chat and other extra interfaces
        JPanel sidePanel = GroupLayout.makeVStretchBox(5);
        sidePanel.setOpaque(false);

        // add a big fat label
        MultiLineLabel vlabel = new MultiLineLabel(controller.getMessage(Constants.MSG_TITLE));
        vlabel.setAntiAliased(true);
        vlabel.setFont(ToyBoxUI.fancyFont);
        sidePanel.add(vlabel, GroupLayout.FIXED);

        // add a chat box
        sidePanel.add(new ChatPanel(controller.getToyBoxContext()));

        // add a history panel
        HistoryPanel historyDisplay = new HistoryPanel(controller);
        historyDisplay.setOpaque(false);
        sidePanel.add(historyDisplay, GroupLayout.FIXED);

        // add a "back to lobby" button
        JButton back = new JButton();
        back.addActionListener(new ActionListenerImplementation(controller));
        back.setText(controller.getMessage(Constants.MSG_BACK_TO_LOBBY));
        sidePanel.add(back, GroupLayout.FIXED);

        // add our side panel to the main display
        add(sidePanel, BorderLayout.EAST);
    }

    /** The interface PlaceView is only implemented as a marker interface. Nothing to do here. */
    public void didLeavePlace(PlaceObject placeObject)
    {
    }

    /** The interface PlaceView is only implemented as a marker interface. Nothing to do here. */
    public void willEnterPlace(PlaceObject placeObject)
    {
    }

}
