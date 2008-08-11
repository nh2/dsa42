package de.schelklingen2008.poker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.schelklingen2008.poker.client.Constants;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel implements Serializable
{

    private List<Player> playerList = new ArrayList<Player>();
    // private List<Pot> potList = new ArrayList<Pot>();
    private long         pot;
    private long         highestBet;
    private int          highestBetIndex;

    private List<Card>   stack      = new ArrayList<Card>();  // Liste aller 52 Karten, werden mit
    // der
    // Zeit an cardList und auf die Spieler
    // verteilt, d.h. die Liste wird kleiner
    // = Kartenstapel
    private List<Card>   cardList   = new ArrayList<Card>();  // Karten in der Mitte
    private int          phase;
    private int          actPlayerIndex;
    private int          dealerIndex;
    private long         smallBlind;
    private boolean      bigBlindNeedsToSet;

    public GameModel()
    {

    }

    public GameModel(String[] names)
    {
        setPlayers(names);
    }

    public boolean isFinished()
    {
        return false;
    }

    public long getPot()
    {
        return pot;
    }

    public List<Player> getPlayerList()
    {
        return playerList;
    }

    // public List<Pot> getPotList()
    // {
    // return potList;
    // }

    public long getHighestBet()
    {
        return highestBet;
    }

    public List<Card> getStack()
    {
        return stack;
    }

    public int getHighestBetIndex()
    {
        return highestBetIndex;
    }

    public void setHighestBetIndex(int highestBetIndex)
    {
        this.highestBetIndex = highestBetIndex;
    }

    public void setHighestBet(long highestBet)
    {
        this.highestBet = highestBet;
    }

    public void setCardList(List<Card> cardList)
    {
        this.cardList = cardList;
    }

    public void setPhase(int phase)
    {
        this.phase = phase;
    }

    public void setActPlayerIndex(int actPlayerIndex)
    {
        this.actPlayerIndex = actPlayerIndex;
    }

    public void setDealerIndex(int dealerIndex)
    {
        this.dealerIndex = dealerIndex;
    }

    public void setSmallBlind(long smallBlind)
    {
        this.smallBlind = smallBlind;
    }

    public List<Card> getCardList()
    {
        return cardList;
    }

    public int getPhase()
    {
        return phase;
    }

    public int getActPlayerIndex()
    {
        return actPlayerIndex;
    }

    public Player getActPlayer()
    {
        Player actPlayer = playerList.get(actPlayerIndex);
        return actPlayer;
    }

    public int getDealerIndex()
    {
        return dealerIndex;
    }

    public long getSmallBlind()
    {
        return smallBlind;
    }

    public void fillStack()
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 13; j++)
            {
                stack.add(new Card(i, j));
            }
        }
    }

    private void initGame()
    {
        smallBlind = Constants.SMALL_BLIND;
        dealerIndex = -1;
        nextRound();
    }

    public Card getRandomCard()
    {

        int index = (int) (Math.random() * (stack.size() - 1));
        Card card1 = stack.get(index);
        stack.remove(index);
        return card1;
    }

    public void fillCardList(int count)
    {
        for (int i = 0; i < count; i++)
        {
            cardList.add(getRandomCard());
        }
    }

    public void addPlayer(String name)
    {
        playerList.add(new Player(name));
    }

    public void giveCardsToPlayers()
    {
        for (Iterator<Player> iterator = playerList.iterator(); iterator.hasNext();)
        {

            Player player = iterator.next();
            player.setCard1(getRandomCard());
            player.setCard2(getRandomCard());

        }
    }

    // public void addPot()
    // {
    // for (Iterator iterator = playerList.iterator(); iterator.hasNext();)
    // {
    // type type = (type) iterator.next();
    //
    // }
    // }

    public void setPot(long content)
    {
        pot = content;
    }

    public boolean mustCallOrReRaise(int playerIndex)
    {
        if (playerIsTurnHolder(playerIndex))
        {
            if (playerList.get(playerIndex).getOwnBet() < highestBet)
            {
                return true;
            }
        }
        return false;
    }

    public boolean mustCheckOrRaise(int playerIndex)
    {
        if (playerIsTurnHolder(playerIndex) == true && playerList.get(playerIndex).getOwnBet() == highestBet)
        {
            return true;
        }
        return false;
    }

    public boolean playerIsTurnHolder(int playerIndex)
    {
        if (actPlayerIndex == playerIndex)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public long getMinBet()
    {
        long minBet = smallBlind * 2;
        long callValue = highestBet - playerList.get(actPlayerIndex).getOwnBet();
        if (callValue > 0)
        {
            long actBal = playerList.get(actPlayerIndex).getBalance() - callValue;
            if (minBet > actBal)
            {
                return 0;
            }
        }
        return minBet;
    }

    public long getMaxBet()
    {
        return playerList.get(actPlayerIndex).getBalance();
    }

    public void nextPlayer()
    {
        actPlayerIndex = getRisenPlayerIndex(actPlayerIndex, 1);
        if (highestBetIndex == actPlayerIndex && bigBlindNeedsToSet == false)
        {
            nextPhase();
        }
        if (highestBetIndex == getRisenPlayerIndex(actPlayerIndex, 1) && bigBlindNeedsToSet == true)
        {
            bigBlindNeedsToSet = false;
        }
        if (playerList.get(actPlayerIndex).isStillIn() == false || playerList.get(actPlayerIndex).hasLost() == true)
        {
            nextPlayer();
        }
    }

    public void nextPhase()
    {
        phase++;
        highestBet = 0;
        actPlayerIndex = getRisenPlayerIndex(dealerIndex, 1);
        highestBetIndex = actPlayerIndex;
        for (Iterator<Player> iterator = playerList.iterator(); iterator.hasNext();)
        {
            Player player = iterator.next();
            player.setStillIn(true);
            player.setOwnBet(0);
        }
        switch (phase)
        {
            case 1:
                fillCardList(3);
                break;
            case 2:
                fillCardList(1);
                break;
            case 3:
                fillCardList(1);
                break;
            case 4:
                computeWinner();
                nextRound();
                break;
        }
    }

    public void computeWinner()
    {
        // TODO Gewinner berechnen, Pot verteilen, Werte resetten, DealerIndex um 1 erhöhen
    }

    public void nextRound()
    {
        phase = 0;
        stack.clear();
        fillStack();
        bigBlindNeedsToSet = true;
        dealerIndex = getRisenPlayerIndex(dealerIndex, 1);
        actPlayerIndex = 0;
        setPlayerBet(smallBlind, getRisenPlayerIndex(dealerIndex, 1), false);
        setPlayerBet(2 * smallBlind, getRisenPlayerIndex(dealerIndex, 2), false);
        getHighestBetAndBetterIndex();
        actPlayerIndex = getRisenPlayerIndex(dealerIndex, 3);
        giveCardsToPlayers();
    }

    public void setPlayerBet(long betValue, int playerIndex, boolean minMaxBorderCheck)
    {
        Player player = playerList.get(playerIndex);
        if (betValue < 2 * smallBlind && playerList.get(playerIndex).getBalance() < smallBlind)
        {
            betValue = player.getBalance();
            player.setOwnBet(betValue);
            player.setBalance(0);
            pot += betValue;
        }
        else
        {
            if (minMaxBorderCheck == true && (betValue < getMinBet() || betValue > getMaxBet()))
            {
                throw new IllegalStateException();
            }
            else
            {
                player.setBalance(player.getBalance() - betValue);
                player.setOwnBet(betValue);
                pot += betValue;
            }
        }
    }

    public void getHighestBetAndBetterIndex()
    {
        long actHighestBet = 0;
        int highestBetIndexStart = highestBetIndex;
        for (int i = 0; i < playerList.size(); i++)
        {
            int risenIndex = getRisenPlayerIndex(highestBetIndexStart, i);
            Player player = playerList.get(risenIndex);
            if (player.getOwnBet() > actHighestBet)
            {
                actHighestBet = player.getOwnBet();
                highestBetIndex = risenIndex;
                highestBet = actHighestBet;
            }
        }
    }

    public int getRisenPlayerIndex(int playerIndex, int toRaise)
    {
        for (int i = 0; i < toRaise; i++)
        {
            playerIndex++;
            if (playerList.size() == playerIndex)
            {
                playerIndex = 0;
            }
        }
        return playerIndex;
    }

    public void call(int playerIndex)
    {
        Player player = playerList.get(playerIndex);
        long callValue = highestBet - player.getOwnBet();
        if (mustCallOrReRaise(playerIndex) == true && player.getBalance() >= callValue)
        {
            setPlayerBet(callValue, playerIndex, false);
            getHighestBetAndBetterIndex();
            nextPlayer();
        }
        else
            throw new IllegalStateException();
    }

    public void check(int playerIndex)
    {
        if (mustCheckOrRaise(playerIndex) == true)
        {
            nextPlayer();
        }
        else
            throw new IllegalStateException();
    }

    public void raise(int playerIndex, long raiseValue)
    {
        if (mustCheckOrRaise(playerIndex) == true && getActPlayer().getBalance() >= highestBet)
        {
            setPlayerBet(raiseValue, playerIndex, true);
            getHighestBetAndBetterIndex();
            nextPlayer();
        }
        else
            throw new IllegalStateException();
    }

    public void reRaise(int playerIndex, long reRaiseValue) // Betrag der noch extra auf den schon vorhandenen
    // Betrag
    // draufgelegt wurde
    {
        long value = highestBet - getActPlayer().getOwnBet() + reRaiseValue;

        if (mustCallOrReRaise(playerIndex) == true && getActPlayer().getBalance() >= value)
        {
            setPlayerBet(highestBet + reRaiseValue, playerIndex, true);
            getHighestBetAndBetterIndex();
            nextPlayer();
        }
        else
            throw new IllegalStateException();
    }

    public void fold(int playerIndex)
    {
        if (playerIsTurnHolder(playerIndex) == true)
        {
            getActPlayer().setStillIn(false);
            nextPlayer();
        }
        else
        {
            throw new IllegalStateException();
        }
    }

    public void setPlayers(String[] names)
    {
        for (int i = 0; i < names.length; i++)
        {
            playerList.add(new Player(names[i]));
        }
        initGame();
    }

    public void printCards()
    {
        for (Iterator<Card> iterator = cardList.iterator(); iterator.hasNext();)
        {
            Card card = iterator.next();
            System.out.println(card.getSuit());
            System.out.println(card.getValue());

        }
    }
}