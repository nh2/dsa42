package de.schelklingen2008.billiards.client.controller;

import de.schelklingen2008.billiards.client.view.BoardView;
import de.schelklingen2008.billiards.model.GameModel;

public class BoardProcessThread extends Thread
{

    private GameModel gameModel;
    private BoardView boardView;

    public BoardProcessThread(GameModel gameModel, BoardView boardView)
    {
        this.gameModel = gameModel;
        this.boardView = boardView;
    }

    @Override
    public void run()
    {

        long lastTick = System.currentTimeMillis();

        while (gameModel.isInMotion())
        {
            long tick = System.currentTimeMillis();

            boardView.repaint();
            for (int i = 0; i < tick - lastTick; i += 10)
            {
                gameModel.processTimeStep(0.01d);
            }
            lastTick = tick;

            try
            {
                Thread.sleep(50); // TODO Adjust this
            }
            catch (InterruptedException e)
            {
            }

        }

    }

}
