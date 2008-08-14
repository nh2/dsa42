package de.schelklingen2008.risk.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.samskivert.swing.GroupLayout;
import com.samskivert.swing.MultiLineLabel;
import com.threerings.crowd.client.PlaceView;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.toybox.client.ChatPanel;
import com.threerings.toybox.client.ToyBoxUI;

import de.schelklingen2008.risk.client.Constants;
import de.schelklingen2008.risk.client.controller.Controller;
import de.schelklingen2008.risk.client.controller.GameChangeListener;

/**
 * Contains the primary client interface for the game.
 */
public class GamePanel extends JPanel implements PlaceView, GameChangeListener
{

    private JTextArea  historyDisplay = new JTextArea();
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
            if (e.getActionCommand().equals(controller.getMessage(Constants.MSG_END_OF_TURN)))
            {
                if (controller.getGameContext().getMyPlayer().equals(
                                                                     controller.getGameContext()
                                                                               .getGameModel()
                                                                               .getTurnholder()))
                {
                    controller.EndofTurn();
                }
            }
            else
            {
                controller.leaveButtonClicked();
            }

        }
    }

    /**
     * Creates a TestGame panel and its associated interface components.
     * @
     */
    public GamePanel(Controller controller)
    {
        this.controller = controller;
        controller.addChangeListener(this);

        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());
        setBackground(new Color(0x6699CC));

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

        // add a standard turn display
        TurnPanel turnDisplay = new TurnPanel(controller);
        turnDisplay.setOpaque(false);
        sidePanel.add(turnDisplay, GroupLayout.FIXED);

        // add a "end turn" button
        JButton turn = new JButton();
        turn.addActionListener(new ActionListenerImplementation(controller));
        turn.setText(controller.getMessage(Constants.MSG_END_OF_TURN));
        sidePanel.add(turn, GroupLayout.FIXED);

        // add a history panel
        historyDisplay.setForeground(Color.RED);
        historyDisplay.setOpaque(false);
        historyDisplay.setEditable(false);
        JScrollPane j = new JScrollPane(historyDisplay);
        j.setPreferredSize(new Dimension(250, 400));
        sidePanel.add(j, GroupLayout.FIXED);

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

    public void addHistoryLine(String s)
    {

        historyDisplay.append(s + "\n");
    }

    public void gameChanged()
    {
        if (controller.getGameContext().getGameModel().getnewHistory()[0] != null)
        {
            addHistoryLine(controller.getGameContext().getGameModel().getnewHistory()[0]);
            if (controller.getGameContext().getGameModel().getnewHistory()[1] != null)
            {
                addHistoryLine(controller.getGameContext().getGameModel().getnewHistory()[1]);
            }

            controller.getGameContext().getGameModel().removenewHistory();
        }

    }
}
