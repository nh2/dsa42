package de.schelklingen2008.poker.client;

import javax.swing.JComponent;

import com.threerings.toybox.util.GameViewTest;
import com.threerings.toybox.util.ToyBoxContext;

import de.schelklingen2008.poker.client.controller.Controller;
import de.schelklingen2008.poker.client.model.GameContext;
import de.schelklingen2008.poker.client.view.BoardView;
import de.schelklingen2008.poker.model.GameModel;

/**
 * A test harness for our board view.
 */
public class BoardViewTest extends GameViewTest
{

    public static void main(String[] args)
    {
        BoardViewTest test = new BoardViewTest();
        test.display();
    }

    @Override
    protected JComponent createInterface(ToyBoxContext tbc)
    {
        Controller controller = new Controller();

        GameContext ctx = controller.getGameContext();
        ctx.setMyName("Matthias");

        GameModel model = controller.getGameContext().getGameModel();
        model.setPlayers(new String[] { "Tobias", "Matthias", "Georg", "Ben" });
        model.fillStack();
        model.fillCardList(3);
        model.fillCardList(1);
        model.fillCardList(1);

        // for (Iterator iterator = model.getCardList().iterator(); iterator.hasNext();)
        // {
        // Card card = (Card) iterator.next();
        // System.out.println(BoardView.getFileName(card));
        // }
        // System.out.println(model.getStack().size());
        // System.out.println(model.getCardList().size());

        model.giveCardsToPlayers();

        // for (Iterator iterator = model.getPlayerList().iterator(); iterator.hasNext();)
        // {
        // Player player = (Player) iterator.next();
        // System.out.println(player.getName());
        // System.out.println(player.getCard1().getName());
        // System.out.println(player.getCard2().getName());
        //
        // }

        model.setPot(0);
        model.setHighestBet(30);
        model.setPhase(3);
        model.setActPlayerIndex(1);
        model.setDealerIndex(0);
        model.setSmallBlind(20);

        return new BoardView(controller);

    }
}
