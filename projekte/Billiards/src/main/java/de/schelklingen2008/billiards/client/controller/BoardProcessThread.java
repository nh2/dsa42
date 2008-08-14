package de.schelklingen2008.billiards.client.controller;

import de.schelklingen2008.billiards.client.view.BoardView;

public class BoardProcessThread extends Thread
{

    private Controller controller;
    private BoardView boardView;

    public BoardProcessThread(Controller controller, BoardView boardView)
    {
        this.controller = controller;
        this.boardView = boardView;
    }

    @Override
    public void run()
    {

        long lastTick = System.currentTimeMillis();

        while (controller.getGameContext().getGameModel().isInMotion())
        {

            long tick = System.currentTimeMillis();

            boardView.repaint();
            for (int i = 0; i < tick - lastTick; i += 10)
            {
                controller.getGameContext().getGameModel().processTimeStep(0.01d);
            }
            lastTick = tick;

            try
            {
                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
            }

        }

    }

}
