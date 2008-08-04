package de.schelklingen2008.billiards.transport;

import com.threerings.parlor.game.data.GameObject;

/**
 * Keeps the shared state of the game for client-server communication and updates.
 * 
 * Note, that all fields must be public.
 * 
 * Note, that a call to "Generate Distributed Objects" is necessary after any change to this class.
 */
public class SharedState extends GameObject
{
    // TODO add fields describing the game state and start code generation afterewards

    public String[] getPlayerNames()
    {
        String[] names = new String[players.length];
        for (int i = 0; i < players.length; i++)
            names[i] = players[i].toString();
        return names;
    }
}
