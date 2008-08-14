package de.schelklingen2008.billiards.client.controller;

import de.schelklingen2008.billiards.client.view.BoardView;

public class BoardProcessThread extends Thread
{

    private Controller controller;
    private BoardView boardView;
    private boolean doCancel;

    public BoardProcessThread(Controller controller, BoardView boardView)
    {
        this.controller = controller;
        this.boardView = boardView;
    }

    @Override
    public void run()
    {

        long lastTick = System.currentTimeMillis();

        while (!doCancel && controller.getGameContext().getGameModel().isInMotion())
        {
            long tick = System.currentTimeMillis();

            boardView.repaint();
            for (int i = 0; i < tick - lastTick && !doCancel; i += 1)
            {
                controller.getGameContext().getGameModel().processTimeStep(0.001d);
            }
            lastTick = tick;

            try
            {
                Thread.sleep(20);
            }
            catch (InterruptedException e)
            {
            }

        }

    }

    public void cancel()
    {
        doCancel = true;
    }

}
