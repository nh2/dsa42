package de.schelklingen2008.poker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import de.schelklingen2008.poker.client.Constants;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel implements Serializable
{

    private static final Random RAND       = new Random();

    private List<Player>        playerList = new ArrayList<Player>();
    // private List<Pot> potList = new ArrayList<Pot>();
    private long                pot;
    private long                highestBet;
    private int                 highestBetIndex;

    private List<Card>          stack      = new ArrayList<Card>();  // Liste aller 52 Karten, werden mit
    // der
    // Zeit an cardList und auf die Spieler
    // verteilt, d.h. die Liste wird kleiner
    // = Kartenstapel
    private List<Card>          cardList   = new ArrayList<Card>();  // Karten in der Mitte
    private List<Player>        winnerList = new ArrayList<Player>();
    private long                winnerValue;
    private int                 phase;
    private int                 actPlayerIndex;
    private int                 dealerIndex;
    private long                smallBlind;
    private boolean             bigBlindNeedsToSet;

    public GameModel()
    {
    }

    public GameModel(String[] names)
    {
        setPlayers(names);
    }

    public List<Player> getWinnerList()
    {
        return winnerList;
    }

    public long getWinnerValue()
    {
        return winnerValue;
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

    public void setOkayWithNextRound(int playerIndex, boolean isOkayWithNextRound)
    {
        Player player = playerList.get(playerIndex);
        player.setOkayWithNextRound(isOkayWithNextRound);
        int count = 0;
        for (Player playerFor : playerList)
        {
            if (playerFor.isOkayWithNextRound()) count++;
        }
        if (count == playerList.size()) nextRound();
    }

    public boolean isOkayWithNextRound(int playerIndex)
    {
        return playerList.get(playerIndex).isOkayWithNextRound();
    }

    private void initGame()
    {
        smallBlind = Constants.SMALL_BLIND;
        dealerIndex = -1;
        nextRound();
    }

    public Card getRandomCard()
    {
        int index = RAND.nextInt(stack.size());
        Card card = stack.get(index);
        stack.remove(index);
        return card;
    }

    public void fillCardList(int count)
    {
        for (int i = 0; i < count; i++)
        {
            cardList.add(getRandomCard());
        }
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
        if (playerIsTurnHolder(playerIndex) && playerList.get(playerIndex).getOwnBet() < highestBet)
        {
            return true;
        }
        return false;
    }

    public boolean mustCheckOrRaise(int playerIndex)
    {
        if (playerIsTurnHolder(playerIndex) && playerList.get(playerIndex).getOwnBet() == highestBet)
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
        int isOutCount = 0;
        for (Player player : playerList)
        {
            if (!player.isStillIn()) isOutCount++;
        }
        if (isOutCount == playerList.size() - 1)
        {
            phase = 3;
            nextPhase();
        }
        if (highestBetIndex == actPlayerIndex && !bigBlindNeedsToSet)
        {
            nextPhase();
        }
        if (!playerList.get(actPlayerIndex).isStillIn() || playerList.get(actPlayerIndex).hasLost())
        {
            nextPlayer();
            return;
        }
        int bigBlindIndex = getRisenPlayerIndex(dealerIndex, 2);
        boolean isBigBlind = bigBlindIndex == actPlayerIndex;
        if (isBigBlind && bigBlindNeedsToSet && highestBetIndex == bigBlindIndex)
        {
            highestBetIndex = getRisenPlayerIndex(highestBetIndex, 1);
        }
        if (isBigBlind) bigBlindNeedsToSet = false;
    }

    public void nextPhase()
    {
        phase++;
        highestBet = 0;
        actPlayerIndex = getRisenPlayerIndex(dealerIndex, 1);
        if (playerList.get(actPlayerIndex).hasLost()) actPlayerIndex = getRisenPlayerIndex(actPlayerIndex, 1);
        highestBetIndex = actPlayerIndex;
        for (Iterator<Player> iterator = playerList.iterator(); iterator.hasNext();)
        {
            Player player = iterator.next();
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
                // nextRound();
                break;
        }
    }

    public void computeWinner()
    {
        Pattern highestPattern = new Pattern(-1, -1, -1);
        // int highestValue1 = -1;
        // int highestValue2 = -1;

        for (Iterator iterator = playerList.iterator(); iterator.hasNext();) // herausfinden, wie hoch das
        // höchste Pattern ist
        {
            Player player = (Player) iterator.next();
            if (player.isStillIn())
            {
                List<Card> cards = new ArrayList<Card>();
                cards.addAll(cardList);
                cards.add(player.getCard1());
                cards.add(player.getCard2());
                // System.out.println(cards.size());
                PatternChecker checker = new PatternChecker(cards);
                if (checker.getHighestPattern().greaterThan(highestPattern))
                {
                    highestPattern = checker.getHighestPattern();
                    // highestValue1 = checker.getPairCard1();
                }
            }
        }

        List<Player> winnerList = new ArrayList<Player>();

        for (Iterator iterator = playerList.iterator(); iterator.hasNext();) // herausfinden, welche Spieler
        // dieses höchste Pattern haben
        {
            Player player = (Player) iterator.next();
            if (player.isStillIn())
            {
                List<Card> cards = new ArrayList<Card>();
                cards.addAll(cardList);
                cards.add(player.getCard1());
                cards.add(player.getCard2());
                System.out.println(cards.size());
                PatternChecker checker = new PatternChecker(cards);
                if (checker.getHighestPattern().equals(highestPattern))
                {

                    winnerList.add(player);
                }
            }
        }

        long winnerValue = pot / winnerList.size();

        for (Iterator<Player> iterator = winnerList.iterator(); iterator.hasNext();)
        {
            Player player = iterator.next();
            player.setBalance(player.getBalance() + winnerValue);
            pot = 0;
            // PatternChecker checker = new PatternChecker()
            // System.out.println("Highest Pattern: " + highestPattern.artToSTring());
        }
        this.winnerList.clear();
        this.winnerValue = winnerValue;
        this.winnerList = winnerList;

    }

    public String kindToString(int patternValue)
    {
        String s;
        switch (patternValue)
        {
            case 0:
                s = "HighCard";
                break;
            case 1:
                s = "Pärchen";
                break;
            case 2:
                s = "Doppelpärchen";
                break;
            case 3:
                s = "Drilling";
                break;
            case 4:
                s = "Straight";
                break;
            case 5:
                s = "Flush";
                break;
            case 6:
                s = "Full House";
                break;
            case 7:
                s = "Vierling";
                break;
            case 8:
                s = "Straight Flush";
                break;
            case 9:
                s = "Royal Flush";
            default:
                s = "Falsche Zahl";
        }
        return s;
    }

    public void nextRound()
    {
        phase = 0;
        pot = 0;
        stack.clear();
        cardList.clear();
        fillStack();
        for (Player player : playerList)
        {
            if (player.getBalance() == 0) player.setLost(true);
            player.setOwnBet(0);
            player.setAllIn(false);
            player.setCard1(null);
            player.setCard2(null);
            player.setStillIn(true);
            player.setOkayWithNextRound(false);
        }
        bigBlindNeedsToSet = true;
        dealerIndex = getRisenPlayerIndex(dealerIndex, 1);
        actPlayerIndex = 0;
        setPlayerBet(smallBlind, getRisenPlayerIndex(dealerIndex, 1), false);
        setPlayerBet(2 * smallBlind, getRisenPlayerIndex(dealerIndex, 2), false);
        setHighestBetAndBetterIndex();
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

    public void setHighestBetAndBetterIndex()
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
            setHighestBetAndBetterIndex();
            nextPlayer();
        }
        else
            throw new IllegalStateException();
    }

    public void check(int playerIndex)
    {
        if (!mustCheckOrRaise(playerIndex)) throw new IllegalStateException();

        nextPlayer();
    }

    public void raise(int playerIndex, long raiseValue)
    {
        if (mustCheckOrRaise(playerIndex) == true && getActPlayer().getBalance() >= highestBet)
        {
            setPlayerBet(raiseValue, playerIndex, true);
            setHighestBetAndBetterIndex();
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
            setHighestBetAndBetterIndex();
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