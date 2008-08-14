import de.schelklingen2008.reversi.model.GameModel;

public class PerformanceTest
{

    public static void main(String[] args)
    {
        GameModel game = new GameModel();

        long tix = System.currentTimeMillis();

        {

            for (int i = 0; i < 1000000; i++)
            {
                GameModel clone = new GameModel(game);
            }
        }

        System.out.println((System.currentTimeMillis() - tix));
    }
}
