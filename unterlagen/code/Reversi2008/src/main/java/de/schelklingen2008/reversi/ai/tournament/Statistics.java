package de.schelklingen2008.reversi.ai.tournament;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class Statistics
{

    private static final double       STEP        = 0.01;
    private static final double       LIMIT       = 0.52;
    private static final int          MATCH_COUNT = 5000;
    private static final int          MC_COUNT    = 1000;
    private static final Random       RANDOM      = new Random();
    private static final NumberFormat PERCENT     = new DecimalFormat("0.0%");

    public static void main(String[] args)
    {
        for (double p = 0.5; p <= 0.7; p += STEP)
        {
            int mcCount = 0;
            for (int mc = 0; mc < MC_COUNT; mc++)
            {
                int winCount = 0;
                for (int match = 0; match < MATCH_COUNT; match++)
                {
                    double r = RANDOM.nextDouble();
                    if (r < p) winCount++;
                }
                if (winCount > LIMIT * MATCH_COUNT) mcCount++;
            }
            System.out.println("p: " + PERCENT.format(p) + " chance: " + PERCENT.format((double) mcCount / MC_COUNT));
        }
    }
}
