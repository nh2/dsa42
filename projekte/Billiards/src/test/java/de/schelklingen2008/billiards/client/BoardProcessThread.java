package de.schelklingen2008.billiards.client;

import de.schelklingen2008.billiards.client.view.BoardView;
import de.schelklingen2008.billiards.model.GameModel;

public class BoardProcessThread extends Thread
{

    private GameModel gameModel;
    private BoardView boardView;

    private BoardProcessThread()
    {

    }

    public BoardProcessThread(GameModel gameModel, BoardView boardView)
    {
        this.gameModel = gameModel;
        this.boardView = boardView;
    }

    @Override
    public void run()
    {

        long lastTick = System.currentTimeMillis();

        while (true)
        {
            long tick = System.currentTimeMillis();

            boardView.repaint();
            gameModel.processTimeStep((tick - lastTick) / 1000d);
            lastTick = tick;

            try
            {
                Thread.sleep(30);
            }
            catch (InterruptedException e)
            {
            }

        }

    }

}
