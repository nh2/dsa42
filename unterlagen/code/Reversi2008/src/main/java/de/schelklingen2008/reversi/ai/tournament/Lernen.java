package de.schelklingen2008.reversi.ai.tournament;

import java.util.Random;

import de.schelklingen2008.reversi.ai.evaluation.BenEvalFunction;
import de.schelklingen2008.reversi.ai.strategy.AlphaBetaStrategy;
import de.schelklingen2008.reversi.ai.strategy.ReversiStrategy;

public class Lernen
{

    private static int[]        sRating   = new int[] { 50, -10, 2, 2, -10, -2, -2, -2, -2, -2 };
    private static final int    FACTOR    = 5;
    private static final int    LIMIT     = FACTOR * FACTOR * FACTOR * FACTOR * FACTOR * FACTOR * FACTOR * FACTOR;
    private static final Random RAND      = new Random();
    private static final int    MATCHES   = 2500;
    private static final int    THRESHOLD = 5200;
    private static final int    CHANGES   = 4;

    public static void main(String[] args)
    {
        BenEvalFunction eOld = new BenEvalFunction();
        BenEvalFunction eNew = new BenEvalFunction();
        ReversiStrategy sOld = new AlphaBetaStrategy(eOld, 1, 0, true, false);
        ReversiStrategy sNew = new AlphaBetaStrategy(eNew, 1, 0, true, false);

        while (true)
        {
            eOld.setSquareRating(sRating.clone());
            changeRating(CHANGES);
            eNew.setSquareRating(sRating.clone());

            TournamentStrategy tOld = new TournamentStrategy("old", sOld);
            TournamentStrategy tNew = new TournamentStrategy("new", sNew);
            Tournament tournament = new Tournament(MATCHES);
            tournament.addStrategy(tOld);
            tournament.addStrategy(tNew);
            tournament.prepare();
            tournament.run();

            boolean significant = tNew.getPoints() > THRESHOLD;
            System.out.println("new rating: "
                               + significant
                               + " "
                               + tNew.getPoints()
                               + "/"
                               + (tNew.getPoints() + tOld.getPoints()));
            printRating();
            if (!significant) sRating = eOld.getSquareRating();
        }
    }

    private static void printRating()
    {
        System.out.println(sRating[0] + " " + sRating[1] + " " + sRating[2] + " " + sRating[3]);
        System.out.println(sRating[1] + " " + sRating[4] + " " + sRating[5] + " " + sRating[6]);
        System.out.println(sRating[2] + " " + sRating[5] + " " + sRating[7] + " " + sRating[8]);
        System.out.println(sRating[3] + " " + sRating[6] + " " + sRating[8] + " " + sRating[9]);
    }

    private static void changeRating(int times)
    {
        for (int i = 0; i < times; i++)
            changeRating();
    }

    private static void changeRating()
    {
        int index = RAND.nextInt(10);
        int oldRating = sRating[index];

        sRating[index] += RAND.nextBoolean() ? 1 : -1;
        // boolean multiply = RAND.nextBoolean();
        // if (oldRating == LIMIT || oldRating == -LIMIT) multiply = false;
        //
        // if (Math.abs(oldRating) == 1 && !multiply)
        // {
        // sRating[index] = -oldRating;
        // }
        // else
        // {
        // sRating[index] = multiply ? oldRating * FACTOR : oldRating / FACTOR;
        // }
    }
}
