package de.schelklingen2008.billiards.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.samskivert.swing.GroupLayout;
import com.samskivert.swing.MultiLineLabel;
import com.threerings.crowd.client.PlaceView;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.toybox.client.ChatPanel;
import com.threerings.toybox.client.ToyBoxUI;

import de.schelklingen2008.billiards.client.Constants;
import de.schelklingen2008.billiards.client.controller.Controller;

/**
 * Contains the primary client interface for the game.
 */
public class GamePanel extends JPanel implements PlaceView
{

    /**
     * 
     */
    private static final long serialVersionUID = 1739531655733929480L;

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
        setBackground(new Color(0x6699CC));

        JPanel box2 = GroupLayout.makeHBox();
        box2.setLayout(new BoxLayout(box2, BoxLayout.LINE_AXIS));
        box2.setOpaque(false);
        add(box2, BorderLayout.WEST);

        JPanel box = GroupLayout.makeHBox();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setOpaque(false);

        BallGauge ballGauge = new BallGauge();
        BoardView boardView = new BoardView(controller, ballGauge);
        BallPanel ballPanel = new BallPanel(controller);

        boardView.setBackground(new Color(0x6699CC));

        box.add(boardView);
        box.add(ballPanel);
        box2.add(box);
        box2.add(Box.createHorizontalStrut(5));
        box2.add(ballGauge);

        // create a side panel to hold our chat and other extra interfaces
        JPanel sidePanel = GroupLayout.makeVStretchBox(5);
        sidePanel.setOpaque(false);

        // add a big fat label
        MultiLineLabel vlabel = new MultiLineLabel(controller.getMessage(Constants.MSG_TITLE));
        vlabel.setAntiAliased(true);
        vlabel.setFont(ToyBoxUI.fancyFont);
        sidePanel.add(vlabel, GroupLayout.FIXED);

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
