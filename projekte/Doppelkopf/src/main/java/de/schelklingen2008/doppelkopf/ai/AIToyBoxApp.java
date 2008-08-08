package de.schelklingen2008.doppelkopf.ai;

import java.io.InputStream;
import java.util.logging.LogManager;

import com.samskivert.util.RunQueue;
import com.threerings.presents.client.Client;
import com.threerings.presents.net.Credentials;
import com.threerings.presents.net.UsernamePasswordCreds;
import com.threerings.util.Name;

/**
 * The launcher application for all ToyBox games.
 */
public class AIToyBoxApp
{

    private static final String SERVER   = "localhost";
    private static final int    PORT     = 47624;
    private static final String PASSWORD = "none";
    private static int          COUNT    = 0;

    public static void main(final String[] args) throws Exception
    {
        InputStream logCfg = AIToyBoxApp.class.getClassLoader().getResourceAsStream("logging.properties");
        LogManager.getLogManager().readConfiguration(logCfg);

        startComputerPlayer();
        Thread.sleep(1000);
        startComputerPlayer();
        Thread.sleep(1000);
        startComputerPlayer();
    }

    private static void startComputerPlayer()
    {
        String username = "cc-" + System.currentTimeMillis() % 100 + "-" + COUNT++;
        Credentials creds = new UsernamePasswordCreds(new Name(username), PASSWORD);
        Client client = new Client(creds, RunQueue.AWT);
        new AIDirector(client);
        client.setServer(SERVER, new int[] { PORT });
        client.logon();
    }
}
