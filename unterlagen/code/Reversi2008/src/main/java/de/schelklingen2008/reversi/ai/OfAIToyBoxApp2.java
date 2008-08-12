package de.schelklingen2008.reversi.ai;

import java.io.InputStream;
import java.util.logging.LogManager;

import com.samskivert.util.RunQueue;
import com.threerings.presents.client.Client;
import com.threerings.presents.net.Credentials;
import com.threerings.presents.net.UsernamePasswordCreds;
import com.threerings.util.Name;

import de.schelklingen2008.reversi.ai.strategy.HorstStrategy;
import de.schelklingen2008.reversi.ai.strategy.ReversiStrategy;

/**
 * The launcher application for all ToyBox games.
 */
public class OfAIToyBoxApp2
{

    public static final ReversiStrategy REVERSI_STRATEGY = new HorstStrategy(5);

    public static void main(final String[] args) throws Exception
    {
        InputStream logCfg = OfAIToyBoxApp2.class.getClassLoader().getResourceAsStream("logging.properties");
        LogManager.getLogManager().readConfiguration(logCfg);
        String strategyName = REVERSI_STRATEGY.toString();
        String username = "cc" + strategyName + System.nanoTime() % 1000;
        String server = "localhost";
        int port = 47624;
        String password = "none";

        Credentials creds = new UsernamePasswordCreds(new Name(username), password);
        Client client = new Client(creds, RunQueue.AWT);
        new AIDirector(client);
        client.setServer(server, new int[] { port });
        client.logon();
    }
}
