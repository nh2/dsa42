package de.schelklingen2008.billiards.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import de.schelklingen2008.billiards.client.controller.Controller;
import de.schelklingen2008.billiards.model.Ball;
import de.schelklingen2008.billiards.model.GameModel;
import de.schelklingen2008.billiards.model.Ball.BallType;

public class BallView extends JPanel
{

    /**
     * 
     */
    private static final long serialVersionUID = -8866337375992404949L;
    Controller controller = null;

    public BallView(Controller controller)
    {
        this.controller = controller;
        setOpaque(false);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(600, 60);
    }

    @Override
    public void paint(Graphics g)
    {
        // TODO Auto-generated method stub
        super.paint(g);
        Graphics2D gfx = (Graphics2D) g;
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GameModel gameModel = controller.getGameContext().getGameModel();

        g.setColor(Color.RED);

        final double BALL_RADIUS = 20;
        final double STRIPE_HEIGHT = 0.6 * 2 * BALL_RADIUS;

        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                Ball ball = gameModel.getBalls().get(i * 2 + j + 2);

                if (ball.getType() == BallType.STRIPED)
                {
                    gfx.setColor(Color.WHITE);
                }
                else
                {
                    gfx.setColor(ball.getColor());
                }

                gfx.fillOval(i * 50 + 10, j * 50 + 10, 40, 40);

                final int x =
                    (int) Math.round(Math.sqrt(1 - 0.25 * STRIPE_HEIGHT / BALL_RADIUS * STRIPE_HEIGHT / BALL_RADIUS)
                                     * BALL_RADIUS);

                final int y = (int) (0.5 * STRIPE_HEIGHT);

                final int angle = (int) Math.round(Math.atan((double) y / (double) x) * 180 / Math.PI);

                if (ball.getType() == BallType.STRIPED)
                {
                    gfx.setColor(ball.getColor());
                    gfx.fillArc(Math.round(i * 50 + 10), Math.round(j * 50 + 10), (int) Math.round(2 * BALL_RADIUS),
                                (int) Math.round(2 * BALL_RADIUS), -angle, 2 * angle);

                    gfx.fillArc(Math.round(i * 50 + 10), Math.round(j * 50 + 10), (int) Math.round(2 * BALL_RADIUS),
                                (int) Math.round(2 * BALL_RADIUS), 180 - angle, 2 * angle);
                    gfx.fillRect((int) (i * 50 + 10 - x + BALL_RADIUS), (int) (j * 50 + 10 + BALL_RADIUS - y), 2 * x,
                                 2 * y);
                }
            }
        }
    }
}
