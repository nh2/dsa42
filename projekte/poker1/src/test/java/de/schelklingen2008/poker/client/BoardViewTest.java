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

        GameModel model = controller.getGameContext().getGameModel();

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

        model.addPlayer("Tobias");
        model.addPlayer("Matthias");
        model.addPlayer("Georg");
        model.addPlayer("Ben");

        model.giveCardsToPlayers();

        // for (Iterator iterator = model.getPlayerList().iterator(); iterator.hasNext();)
        // {
        // Player player = (Player) iterator.next();
        // System.out.println(player.getName());
        // System.out.println(player.getCard1().getName());
        // System.out.println(player.getCard2().getName());
        //
        // }

        model.setPot(500);
        model.setHighestBet(200);
        model.setPhase(3);
        model.setActPlayerIndex(1);
        model.setDealerIndex(0);
        model.setSmallBlind(20);

        GameContext ctx = controller.getGameContext();
        ctx.setPlayers(new String[] { "dick", "doof" });
        ctx.setMyName("dick");
        return new BoardView(controller);

    }
}
