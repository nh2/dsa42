package de.schelklingen2008.reversi.transport;

import com.threerings.parlor.game.data.GameObject;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

/**
 * Keeps the shared state of the game for client-server communication and updates. Note, that all fields must
 * be public. Note, that a call to "Generate Distributed Objects" is necessary after any change to this class.
 */
public class SharedState extends GameObject
{

    public GameModel model;

    public Player[][] getBoard()
    {
        int size = GameModel.SIZE;
        Player[][] copy = new Player[size][size];

        if (model == null) return copy;
        Player[][] board = model.getBoard();
        if (board == null) return copy;
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++)
                copy[x][y] = board[x][y];
        return copy;
    }

    public Player getTurnHolder()
    {
        if (model == null) return null;
        return model.getTurnHolder();
    }

    public String[] getPlayerNames()
    {
        String[] names = new String[players.length];
        for (int i = 0; i < players.length; i++)
            names[i] = players[i].toString();
        return names;
    }

    // AUTO-GENERATED: FIELDS START
    /** The field name of the <code>model</code> field. */
    public static final String MODEL = "model";

    // AUTO-GENERATED: FIELDS END

    // AUTO-GENERATED: METHODS START
    /**
     * Requests that the <code>model</code> field be set to the specified value. The local value will be
     * updated immediately and an event will be propagated through the system to notify all listeners that the
     * attribute did change. Proxied copies of this object (on clients) will apply the value change when they
     * received the attribute changed notification.
     */
    public void setModel(GameModel value)
    {
        GameModel ovalue = model;
        requestAttributeChange(MODEL, value, ovalue);
        model = value;
    }
    // AUTO-GENERATED: METHODS END
}
