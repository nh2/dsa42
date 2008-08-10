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

    public GameModel()
    {
        initGame();
    }

    public GameModel(String[] names)
    {
        setPlayers(names);
        initGame();
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
        setSmallBlind(Constants.SMALL_BLIND);
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
        for (Iterator iterator = playerList.iterator(); iterator.hasNext();)
        {

            Player player = (Player) iterator.next();
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
        if (actPlayerIndex == playerIndex)
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
        if (actPlayerIndex == playerIndex)
        {
            if (playerList.get(playerIndex).getOwnBet() == highestBet)
            {
                return true;
            }
        }
        return false;
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
        if (actPlayerIndex == playerList.size())
        {
            actPlayerIndex = 0;
        }
        if (highestBetIndex == actPlayerIndex)
        {
            nextPhase();
        }
        if (playerList.get(actPlayerIndex).isStillIn() == false || playerList.get(actPlayerIndex).hasLost() == false)
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
        for (Iterator iterator = playerList.iterator(); iterator.hasNext();)
        {
            Player player = (Player) iterator.next();
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
                // computeWinner();
                nextRound();
                break;
        }
    }

    public void nextRound()
    {
        phase = 0;
        stack.clear();
        fillStack();
        dealerIndex = getRisenPlayerIndex(dealerIndex, 1);
        playerList.get(getRisenPlayerIndex(dealerIndex, 1)).setOwnBet(smallBlind);
        playerList.get(getRisenPlayerIndex(dealerIndex, 2)).setOwnBet(2 * smallBlind);
        pot = playerList.get(getRisenPlayerIndex(dealerIndex, 1)).getOwnBet();
        pot += playerList.get(getRisenPlayerIndex(dealerIndex, 2)).getOwnBet();
        actPlayerIndex = getRisenPlayerIndex(dealerIndex, 3);
        getHighestBetterIndex();
        giveCardsToPlayers();
    }

    public void getHighestBetterIndex()
    {
        long actHighestBet = 0;
        for (int i = 0; playerList.size() < i; i++)
        {
            Player player = playerList.get(i);
            if (player.getOwnBet() > actHighestBet)
            {
                highestBetIndex = i;
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

    public void call()
    {
        Player actPlayer = playerList.get(actPlayerIndex);
        long callValue = highestBet - actPlayer.getOwnBet();
        if (mustCallOrReRaise(actPlayerIndex) == true && actPlayer.getBalance() >= callValue)
        {
            actPlayer.setBalance(actPlayer.getBalance() - callValue);
            pot = pot + callValue;
            nextPlayer();
        }
    }

    public void check()
    {
        if (mustCheckOrRaise(actPlayerIndex) == true)
        {
            nextPlayer();
        }
    }

    public void raise(long raiseValue)
    {
        if (mustCheckOrRaise(actPlayerIndex) == true && getActPlayer().getBalance() >= highestBet)
        {
            getActPlayer().setBalance(getActPlayer().getBalance() - raiseValue);
            pot = pot + raiseValue;
            nextPlayer();
        }
    }

    public void reRaise(long reRaiseValue) // Betrag der noch extra auf den schon vorhandenen Betrag
    // draufgelegt wurde
    {
        long value = highestBet - getActPlayer().getOwnBet() + reRaiseValue;
        {
            if (mustCallOrReRaise(actPlayerIndex) == true && getActPlayer().getBalance() >= value)
            {
                getActPlayer().setBalance(getActPlayer().getBalance() - value);
                pot = pot + value;
            }
        }
        nextPlayer();
    }

    public void fold()
    {
        getActPlayer().setStillIn(false);
        nextPlayer();
    }

    public void setPlayers(String[] names)
    {
        for (int i = 0; i < names.length; i++)
            getPlayerList().add(new Player(names[i]));
    }

    public void kartenAusgeben()
    {
        for (Iterator iterator = cardList.iterator(); iterator.hasNext();)
        {
            Card card = (Card) iterator.next();
            System.out.println(card.getSuit());
            System.out.println(card.getValue());

        }
    }
}