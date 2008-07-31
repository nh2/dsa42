package de.schelklingen2008.reversi.ai;

import java.util.Iterator;
import java.util.logging.Logger;

import com.threerings.crowd.client.LocationService;
import com.threerings.crowd.data.PlaceConfig;
import com.threerings.parlor.client.ParlorDecoder;
import com.threerings.parlor.client.ParlorReceiver;
import com.threerings.parlor.client.TableService;
import com.threerings.parlor.data.Table;
import com.threerings.parlor.data.TableConfig;
import com.threerings.parlor.game.data.GameConfig;
import com.threerings.presents.client.Client;
import com.threerings.presents.client.ClientAdapter;
import com.threerings.presents.client.InvocationService;
import com.threerings.presents.dobj.DObjectManager;
import com.threerings.presents.dobj.DSet;
import com.threerings.presents.dobj.ObjectAccessException;
import com.threerings.presents.dobj.Subscriber;
import com.threerings.toybox.client.ToyBoxService;
import com.threerings.toybox.data.ToyBoxGameConfig;
import com.threerings.toybox.lobby.data.LobbyConfig;
import com.threerings.toybox.lobby.data.LobbyObject;
import com.threerings.util.Name;
import com.threerings.util.StreamableHashMap;

import de.schelklingen2008.reversi.client.model.GameContext;
import de.schelklingen2008.reversi.transport.SharedState;
import de.schelklingen2008.util.LoggerFactory;

public class AIDirector implements GameEventListener
{

    private static final String AI_PREFIX = "cc";

    private static final Logger sLogger = LoggerFactory.create();

    private static final String TOYBOX_SERVICES = "toybox";
    private static final String CHAT_SERVICES = "crowd";
    private static final String PARLOR_SERVICES = "parlor";

    private static final int GAME_ID = -1;

    private final Client mClient;

    private ToyBoxGameConfig mGameConfig;

    private LobbyObject mLobby;

    private int mTableId;

    public AIDirector(final Client pClient)
    {
        mClient = pClient;
        mClient.addServiceGroup(PARLOR_SERVICES);
        mClient.addServiceGroup(CHAT_SERVICES);
        mClient.addServiceGroup(TOYBOX_SERVICES);
        mClient.addClientObserver(CLIENT_ADAPTER);
        mClient.getInvocationDirector().registerReceiver(new ParlorDecoder(PARLOR_RECEIVER));
    }

    private void didLogon()
    {
        sLogger.fine("logged on");

        getToyBoxService().getLobbyOid(mClient, GAME_ID, GET_LOBBY_ID_LISTENER);
    }

    private void gotLobbyOid(final Integer pLobbyOid)
    {
        sLogger.fine("Got lobby oid [oid=" + pLobbyOid + "].");

        mGameConfig = null;
        mLobby = null;
        getObjectManager().subscribeToObject(pLobbyOid, SUBSCRIBE_TO_LOBBY_LISTENER);
    }

    private void moveToLobby()
    {
        getLocationService().moveTo(mClient, mLobby.getOid(), MOVE_TO_LOBBY_LISTENER);
    }

    private void movedToLobby(final LobbyConfig pLobbyCfg)
    {
        sLogger.fine("Moved to lobby [cfg=" + pLobbyCfg + "].");

        initGameCfg(pLobbyCfg);
        createOrJoinTable();
    }

    private void subscribedToLobby(final LobbyObject pLobby)
    {
        sLogger.fine("Subscribed to lobby [lobby=" + pLobby + "].");

        mLobby = pLobby;
        moveToLobby();
    }

    private void createOrJoinTable()
    {
        if (mGameConfig == null || mLobby == null) return;
        sLogger.fine("Create or join table.");

        Table table = getFirstComputerTable();
        if (table == null)
        {
            getTableService().createTable(mClient, createTableCfg(), mGameConfig,
                    CREATE_TABLE_LISTENER);
        }
        else
        {
            mTableId = table.tableId;
            int position = table.players[0] == null ? 0 : 1;
            getTableService().joinTable(mClient, mTableId, position, null);

            sLogger.fine("Joined table [tableId=" + mTableId + "].");
        }
    }

    private void createdTable(final Integer pTableId)
    {
        sLogger.fine("Created table [tableId=" + pTableId + "].");

        mTableId = pTableId;
    }

    private void startedGame(final int pGameOid)
    {
        sLogger.fine("Started game [gameOid=" + pGameOid + "].");

        getLocationService().moveTo(mClient, pGameOid, MOVE_TO_GAME_LISTENER);
        getObjectManager().subscribeToObject(pGameOid, SUBSCRIBE_TO_REVERSI_LISTENER);
    }

    private void movedToGame(final ToyBoxGameConfig pGameCfg)
    {
        sLogger.fine("Moved to game [gameCfg=" + pGameCfg + "].");
    }

    private void subscribedToReversi(final SharedState sharedState)
    {
        sLogger.fine("Subscribed to reversi [reversi=" + sharedState + "].");
        sharedState.initManagerCaller(mClient.getDObjectManager());
        GameContext gameContext = new GameContext();
        gameContext.setMyName(mClient.getCredentials().getUsername().toString());
        gameContext.setPlayers(sharedState.getPlayerNames());
        new ReversiAI(gameContext, sharedState, this);
    }

    public void gameDidEnd()
    {
        moveToLobby();
    }

    private static TableConfig createTableCfg()
    {
        TableConfig tableConfig = new TableConfig();
        tableConfig.desiredPlayerCount = 2;
        tableConfig.minimumPlayerCount = 2;
        tableConfig.privateTable = false;
        tableConfig.teamMemberIndices = null;
        return tableConfig;
    }

    private void initGameCfg(final LobbyConfig pLobbyCfg)
    {
        StreamableHashMap<String, Object> params = new StreamableHashMap<String, Object>();
        params.put("aicount", new Integer(0));
        mGameConfig = new ToyBoxGameConfig(GAME_ID, pLobbyCfg.getGameDefinition());
        mGameConfig.params = params;
    }

    private Table getFirstComputerTable()
    {
        if (mLobby == null) return null;
        DSet<Table> tables = mLobby.getTables();
        for (Iterator<Table> iterator = tables.iterator(); iterator.hasNext();)
        {
            Table table = iterator.next();
            int playerCount = table.getOccupiedCount();
            if (playerCount != 1) continue;

            Name[] occupants = table.players;
            if (occupants == null) continue;

            if (occupants.length == 0) continue;
            Name playerOne = table.players[0];
            if (playerOne != null && playerOne.getNormal().startsWith(AI_PREFIX)) return table;

            if (occupants.length == 1) continue;
            Name playerTwo = table.players[1];
            if (playerTwo != null && playerTwo.getNormal().startsWith(AI_PREFIX)) return table;
        }
        return null;
    }

    private TableService getTableService()
    {
        if (mLobby == null) throw new IllegalStateException("Lobby must be initialized.");
        return mLobby.getTableService();
    }

    private ToyBoxService getToyBoxService()
    {
        return (ToyBoxService) mClient.requireService(ToyBoxService.class);
    }

    private LocationService getLocationService()
    {
        return (LocationService) mClient.requireService(LocationService.class);
    }

    private DObjectManager getObjectManager()
    {
        return mClient.getDObjectManager();
    }

    private final ToyBoxService.ResultListener GET_LOBBY_ID_LISTENER = new ToyBoxService.ResultListener()
    {

        public void requestProcessed(final Object result)
        {
            gotLobbyOid((Integer) result);
        }

        public void requestFailed(final String cause)
        {
            sLogger.warning("Failed to get lobby oid [error=" + cause + "].");
        }
    };

    private final LocationService.MoveListener MOVE_TO_LOBBY_LISTENER = new LocationService.MoveListener()
    {

        public void moveSucceeded(final PlaceConfig config)
        {
            movedToLobby((LobbyConfig) config);
        }

        public void requestFailed(final String cause)
        {
            sLogger.warning("Failed to move to lobby [error=" + cause + "].");
        }
    };
    private final LocationService.MoveListener MOVE_TO_GAME_LISTENER = new LocationService.MoveListener()
    {

        public void moveSucceeded(final PlaceConfig config)
        {
            movedToGame((ToyBoxGameConfig) config);
        }

        public void requestFailed(final String cause)
        {
            sLogger.warning("Failed to move to game [error=" + cause + "].");
        }
    };
    private final Subscriber<LobbyObject> SUBSCRIBE_TO_LOBBY_LISTENER = new Subscriber<LobbyObject>()
    {

        public void objectAvailable(final LobbyObject lobby)
        {
            subscribedToLobby(lobby);
        }

        public void requestFailed(final int oid, final ObjectAccessException cause)
        {
            sLogger.warning("Failed to subscribe to lobby [error=" + cause + "].");
        }

    };

    private final Subscriber<SharedState> SUBSCRIBE_TO_REVERSI_LISTENER = new Subscriber<SharedState>()
    {

        public void objectAvailable(final SharedState sharedState)
        {
            subscribedToReversi(sharedState);
        }

        public void requestFailed(final int oid, final ObjectAccessException cause)
        {
            sLogger.warning("Failed to subscribe to reversi [error=" + cause + "].");
        }
    };

    private final InvocationService.ResultListener CREATE_TABLE_LISTENER = new InvocationService.ResultListener()
    {

        public void requestProcessed(final Object result)
        {
            createdTable((Integer) result);
        }

        public void requestFailed(final String cause)
        {
            sLogger.warning("Failed to create lobby [error=" + cause + "].");
        }
    };

    private final ClientAdapter CLIENT_ADAPTER = new ClientAdapter()
    {

        @Override
        public void clientDidLogon(final Client client)
        {
            didLogon();
        }

        @Override
        public void clientFailedToLogon(final Client c, final Exception cause)
        {
            sLogger.warning("Client failed to logon: " + cause);
            System.exit(0);
        }

        @Override
        public void clientDidLogoff(final Client c)
        {
            sLogger.info("Client logged off.");
            System.exit(0);
        }
    };

    private final ParlorReceiver PARLOR_RECEIVER = new ParlorReceiver()
    {

        public void gameIsReady(final int gameOid)
        {
            startedGame(gameOid);
        }

        public void receivedInvite(final int remoteId, final Name inviter, final GameConfig config)
        {
        }

        public void receivedInviteCancellation(final int remoteId)
        {
        }

        public void receivedInviteResponse(final int remoteId, final int code, final Object arg)
        {
        }

    };
}
