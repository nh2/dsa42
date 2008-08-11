package de.schelklingen2008.canasta.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.logging.Logger;

import de.schelklingen2008.canasta.client.Constants;
import de.schelklingen2008.util.LoggerFactory;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel implements Serializable
{

    private static final Logger sLogger  = LoggerFactory.create();

    private int                 turnHolder;
    private Player[]            players;
    private Talon               talon;
    private Discard             discard;
    private boolean             hasDrawn = false;

    public GameModel(String[] playerNames)
    {
        // only 2 or 4 players allowed for now
        if (playerNames.length != 2 && playerNames.length != 4)
        {
            throw new IllegalArgumentException("Only 2 or 4 players allowed for now!");
        }

        /**
         * TODO Feature: any player order allowed
         */
        // create the player objects
        players = new Player[playerNames.length];
        int i = 0;
        for (String playerName : playerNames)
        {
            players[i] = new Player(playerName);
            i++;
        }

        // create a full 110 card talon
        talon = Talon.getInstance();
        talon.shuffle();

        // create discard pile
        discard = new Discard();

        // draw 15 cards for every player
        for (Player player : players)
        {
            for (int j = 0; j < Constants.GAME_INIT_CARD_COUNT; j++)
            {
                player.getHand().add(talon.pop());
            }
        }

        // discard one card
        discard.push(talon.pop());

        // create outlays

        // for (Player player : players)
        // {
        // Outlay outlay = player.getOutlay();
        // for (int j = 0; j < 10; j++)
        // {
        // CardStack cardStack = new CardStack();
        // for (int k = 0; k < 10; k++)
        // {
        // cardStack.add(new Card(Rank.QUEEN, Suit.HEARTS));
        // }
        // outlay.add(cardStack);
        // }
        // }
    }

    public boolean isTurnHolder(Player player)
    {
        return players[getTurnHolder()] == player;
    }

    public void drawCard(Player player)
    {
        if (!isTurnHolder(player)) return;

        hasDrawn = true;
        Card card = talon.pop();

        player.getHand().add(card);
    }

    public void addCardsToStack(Player player, int[] whichCards, int whichStack)
    {
        Card[] cards = new Card[whichCards.length];

        for (int i = 0; i < whichCards.length; i++)
        {
            cards[i] = player.getHand().get(whichCards[i]);
        }

        CardStack cardStack = player.getOutlay().get(whichStack);

        addCardsToStack(player, cards, cardStack);
    }

    public void addCardsToStack(Player player, Card[] cards, CardStack cardStack)
    {
        if (!isTurnHolder(player)) return;

        sLogger.info("Player " + player.getName() + " should meld cards now");
        sLogger.info(Arrays.toString(cards));

        for (Card card : cards)
        {
            cardStack.add(card);
            player.getHand().remove(card);
        }
    }

    public void discardCard(Player player, int whichCard)
    {
        discardCard(player, player.getHand().get(whichCard));
    }

    public void discardCard(Player player, Card card)
    {
        if (!isTurnHolder(player)) return;
        player.getHand().remove(card);
        discard.push(card);
        if (player.hasCanasta() && player.getHand().size() == 0)
        {
            goOut();
        }
        endTurn();
    }

    public void meldCards(Player player, int[] whichCards)
    {
        Card[] cards = new Card[whichCards.length];

        for (int i = 0; i < whichCards.length; i++)
        {
            cards[i] = player.getHand().get(whichCards[i]);
        }

        meldCards(player, cards);
    }

    public void meldCards(Player player, Card[] cards)
    {
        if (!isTurnHolder(player)) return;

        if (cards.length < Constants.GAME_SMALLEST_MELD) return;

        sLogger.info("Player " + player.getName() + " should make outlay now");
        sLogger.info(Arrays.toString(cards));

        CardStack cardStack = new CardStack();
        addCardsToStack(player, cards, cardStack);
        player.getOutlay().add(cardStack);
    }

    public static boolean isFirstMeldLegal(Player player, Card[] cards)
    {
        int minScore = Constants.GAME_FIRST_MELD[0];

        int i = 0;
        for (int j = 0; j < Constants.GAME_SCORE_LEVEL.length; j++)
        {
            if (player.getTotalScore() >= Constants.GAME_SCORE_LEVEL[i])
            {

                try
                {
                    minScore = Constants.GAME_FIRST_MELD[i + 1];
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    throw new IllegalStateException("The game should already be over, because a player has won", e);
                }
            }
            else
            {
                break;
            }
        }

        int cardScore = 0;
        for (Card card : cards)
        {
            cardScore += getCardValue(card);
        }

        return cardScore >= minScore;
    }

    public static boolean isMeldLegal(Card[] cards, CardStack cardStack)
    {
        if (cards.length <= 0) return false;

        Rank rank = GameModel.getRank(cards);
        if (!rank.isWildcard() && rank != cardStack.getRank())
        {
            return false;
        }

        if ((Card.getJokerCount(cards) + cardStack.getJokerCount()) * 2 >= cardStack.size() + cards.length)
        {
            return false;
        }

        return true;
    }

    public static Rank getRank(Card[] cards)
    {
        Rank rank = null;
        for (Card card : cards)
        {
            if (card.getRank().isWildcard() && rank == null)
                rank = Rank.JOKER;

            else if (card.getRank().isWildcard() && rank != null)
                continue;

            else if (rank == null)
                rank = card.getRank();

            else if (rank == card.getRank())
                continue;

            else
                return null;

        }
        return rank;
    }

    public static int getCardValue(Card card)
    {
        switch (card.getRank())
        {
            case THREE:
            case FOUR:
            case FIVE:
            case SIX:
            case SEVEN:
                return 5;

            case EIGHT:
            case NINE:
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;

            case ACE:
            case TWO:
                return 20;

            case JOKER:
                return 50;

            default:
                return 0;
        }
    }

    public void goOut()
    {

        // TODO round is finished, deal new cards, evaluate score, etc
    }

    public void endTurn()
    {
        hasDrawn = false;
        turnHolder++;
        turnHolder %= players.length;
    }

    public int getTurnHolder()
    {
        return turnHolder;
    }

    public Player[] getPlayers()
    {
        return players;
    }

    public Talon getTalon()
    {
        return talon;
    }

    public Discard getDiscard()
    {
        return discard;
    }

    public boolean isFinished()
    {
        return false;
    }

    public Player getPlayer(String string)
    {
        for (int i = 0; i < players.length; i++)
        {
            if (players[i].getName().equals(string))
            {
                return players[i];
            }
        }
        throw new RuntimeException(new IllegalArgumentException("Playername " + string + " not found in game model"));
    }

    public Player getPlayer(int playerNumber)
    {
        return players[playerNumber];
    }

    public int getPlayerIndex(String string)
    {
        for (int i = 0; i < players.length; i++)
        {
            if (players[i].getName().equals(string))
            {
                return i;
            }
        }
        throw new RuntimeException(new IllegalArgumentException("Playername " + string + " not found in game model"));
    }

    public boolean hasDrawn()
    {
        return hasDrawn;
    }
}