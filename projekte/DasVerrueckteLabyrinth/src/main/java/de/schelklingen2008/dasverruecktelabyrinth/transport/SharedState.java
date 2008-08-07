package de.schelklingen2008.dasverruecktelabyrinth.transport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.threerings.parlor.game.data.GameObject;

import de.schelklingen2008.dasverruecktelabyrinth.model.GameModel;

/**
 * Keeps the shared state of the game for client-server communication and updates. Note, that all fields must
 * be public. Note, that a call to "Generate Distributed Objects" is necessary after any change to this class.
 */
public class SharedState extends GameObject
{

    public byte[] modelBytes;

    public void setModel(GameModel model)
    {
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(model);
            setModelBytes(baos.toByteArray());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public GameModel getModel()
    {
        try
        {
            if (modelBytes == null) return null;
            ByteArrayInputStream bais = new ByteArrayInputStream(modelBytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (GameModel) ois.readObject();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public String[] getPlayerNames()
    {
        String[] names = new String[players.length];
        for (int i = 0; i < players.length; i++)
            names[i] = players[i].toString();
        return names;
    }

    // AUTO-GENERATED: FIELDS START
    /** The field name of the <code>modelBytes</code> field. */
    public static final String MODEL_BYTES = "modelBytes";

    // AUTO-GENERATED: FIELDS END

    // AUTO-GENERATED: METHODS START
    /**
     * Requests that the <code>modelBytes</code> field be set to the specified value. The local value will be
     * updated immediately and an event will be propagated through the system to notify all listeners that the
     * attribute did change. Proxied copies of this object (on clients) will apply the value change when they
     * received the attribute changed notification.
     */
    public void setModelBytes(byte[] value)
    {
        byte[] ovalue = modelBytes;
        requestAttributeChange(MODEL_BYTES, value, ovalue);
        modelBytes = value == null ? null : value.clone();
    }

    /**
     * Requests that the <code>index</code>th element of <code>modelBytes</code> field be set to the specified
     * value. The local value will be updated immediately and an event will be propagated through the system
     * to notify all listeners that the attribute did change. Proxied copies of this object (on clients) will
     * apply the value change when they received the attribute changed notification.
     */
    public void setModelBytesAt(byte value, int index)
    {
        byte ovalue = modelBytes[index];
        requestElementUpdate(MODEL_BYTES, index, Byte.valueOf(value), Byte.valueOf(ovalue));
        modelBytes[index] = value;
    }
    // AUTO-GENERATED: METHODS END
}
