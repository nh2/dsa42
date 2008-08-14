package de.schelklingen2008.jipbo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.schelklingen2008.jipbo.client.Constants;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel implements Serializable
{

    private Card[]     mBuildPile;
    private List<Card> mStockCards;
    private Player[]   mPlayers;

    private Player     turnHolder;

    public GameModel(String[] pNames)
    {
        clear();
        if (pNames != null)
        {
            initialize(pNames);
        }
    }

    public void initialize(String[] pNames)
    {
        mStockCards = shuffleStockCards();

        mBuildPile = new Card[] { new Card(-2), new Card(-2), new Card(-2), new Card(-2), new Card(-1) };

        mPlayers = new Player[pNames.length];
        for (int i = 0; i < pNames.length; i++)
        {

            mPlayers[i] = new Player(pNames[i], shuffleStockPile(), null);
        }
        setTurnHolder(mPlayers[0]);
        refreshDrawPile(mPlayers[0].getName());
    }

    private List<Card> shuffleStockCards()
    {
        List<Card> rStockCards = new ArrayList<Card>();

        for (int i = 1; i <= 12; i++)
        {
            for (int j = 0; j < 12; j++)
            {
                rStockCards.add(new Card(i));
            }

        }
        for (int i = 1; i <= 18; i++)
        {
            rStockCards.add(new Card(0));
        }
        Collections.shuffle(rStockCards);

        return rStockCards;
    }

    private List<Card> shuffleStockPile()
    {
        List<Card> rStockPile = new ArrayList<Card>();

        for (int i = 1; i <= Constants.DURATION; i++)
        {
            rStockPile.add(mStockCards.get(i));
            mStockCards.remove(i);
            Collections.shuffle(mStockCards);
        }

        Collections.shuffle(rStockPile);
        return rStockPile;
    }

    // private Card[] shuffleDrawPile()
    // {
    // Card[] rDrawPile = new Card[5];
    // for (int i = 0; i < rDrawPile.length; i++)
    // {
    // rDrawPile[i] = getLastStockCard();
    // removeLastStockCard();
    // }
    // return rDrawPile;
    // }

    public void refreshDrawPile(String pName)
    {
        Player pPlayer = getPlayerByName(pName);
        if (!pPlayer.equals(turnHolder)) throw new IllegalStateException("It is not the players turn: "
                                                                         + pPlayer.getName()
                                                                         + " (rDP)");
        for (int i = 0; i < pPlayer.getDrawPile().length; i++)
        {
            if (pPlayer.getDrawPile()[i].getNumber() == -2)
            {
                pPlayer.getDrawPile()[i].setNumber(getLastStockCard().getNumber());
                removeLastStockCard();
            }
        }
    }

    public Player[] getPlayers()
    {
        return mPlayers;
    }

    public Player getPlayerIndexOf(int pN)
    {
        return mPlayers[pN];
    }

    public int getPlayerSize()
    {
        return mPlayers != null ? mPlayers.length : 0;
    }

    public String getPlayerNameIndexOf(int pN)
    {
        return mPlayers[pN].getName();
    }

    public Card[] getBuildPile()
    {
        return mBuildPile;
    }

    public List<Card> getStockCards()
    {
        return mStockCards;
    }

    public Card getLastStockCard()
    {
        return mStockCards.get(mStockCards.size() - 1);
    }

    public void removeLastStockCard()
    {
        mStockCards.remove(mStockCards.size() - 1);
    }

    public int getPlayerIDByName(String pName)
    {
        for (int i = 0; i < mPlayers.length; i++)
        {
            if (mPlayers[i].getName().equals(pName)) return i;
        }
        throw new IllegalStateException("no player found.");
    }

    public Player getPlayerByName(String pName)
    {
        return mPlayers[getPlayerIDByName(pName)];
    }

    public void putCard(String pPlayerName, int pFromPlace, boolean pFromHand, int pToPlace)
    {
        int playerID = getPlayerIDByName(pPlayerName);
        Player player = mPlayers[playerID];
        if (!player.equals(turnHolder)) throw new IllegalStateException("It is not the players turn: "
                                                                        + player.getName());

        int cardToPlace = 0;
        if (pFromHand)
        {
            cardToPlace = player.getDrawPile()[pFromPlace].getNumber();
        }
        else
        {
            if (pFromPlace < 4)
            {
                cardToPlace = player.getDiscardPile()[pFromPlace].getNumber();
            }
            else
            {
                cardToPlace = player.getLastStockPile().getNumber();
            }
        }

        Card targetCard = mBuildPile[pToPlace];
        int targetNumber = targetCard.getNumber();
        // here comes the joker
        if (targetNumber == Constants.EMPTY)
        {
            if (cardToPlace == 0) cardToPlace = 1;
            if (cardToPlace != 1) throw new IllegalArgumentException("Card must be a 1 or a joker: " + cardToPlace);
        }
        else
        {
            if (cardToPlace == 0) cardToPlace = targetNumber + 1;
            if (cardToPlace != targetNumber + 1)
            {
                String errMsg = "Card must be one higher than " + targetNumber + ", but was: " + cardToPlace;
                throw new IllegalArgumentException(errMsg);
            }
        }

        // update BuildPile
        targetCard.setNumber(cardToPlace);

        // remove PlayerPiles
        if (pFromHand)
        {
            player.removeDrawPileCard(pFromPlace);
        }
        else
        {
            if (pFromPlace < 4)
            {
                player.removeDiscardPileCard(pFromPlace);
            }
            else
            {
                player.removeLastCardFromStockPile();
            }
        }
    }

    public void placeCardInDiscardPile(String pPlayerName, int pFromPlace, int pCard, int pToPlace)
    {
        if (pCard >= 0 && pToPlace < 4) // must be a normal card - protect from IndexOutOfBounds
        {
            int playerID = getPlayerIDByName(pPlayerName);
            Player player = mPlayers[playerID];
            if (!player.equals(turnHolder)) throw new IllegalStateException("It is not the players turn: "
                                                                            + player.getName()
                                                                            + " (pCIDP)");
            Card[] playerDiscardPile = player.getDiscardPile();

            playerDiscardPile[pToPlace].setNumber(pCard);
            player.removeDrawPileCard(pFromPlace);
            advanceTurn();
        }
    }

    private void advanceTurn()
    {
        int currentTurnHolder = getPlayerIDByName(turnHolder.getName());
        int newTurnHolder = 0;
        if (getPlayerSize() - 1 != currentTurnHolder)
        {
            newTurnHolder = currentTurnHolder + 1;
        }
        setTurnHolder(getPlayerIndexOf(newTurnHolder));
        refreshDrawPile(turnHolder.getName());
    }

    public Player getTurnHolder()
    {
        return turnHolder;
    }

    public void setTurnHolder(Player pTurnHolder)
    {
        turnHolder = pTurnHolder;
    }

    public boolean isTurnHolder(Player player)
    {
        return player == getTurnHolder();
    }

    public boolean isFinished()
    {
        return getTurnHolder() == null;
    }

    private void clear()
    {
        turnHolder = null;
    }

    public boolean isWinner(Player player)
    {
        return getWinner() == player;
    }

    public Player getWinner()
    {
        if (!isFinished()) return null;
        for (int i = 0; i < mPlayers.length; i++)
        {
            if (mPlayers[i].getStockPile().size() == 0)
            {
                return mPlayers[i];
            }
        }
        return null;
    }
}