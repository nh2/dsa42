package fibonacci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FibSimple
{

    public static int fibonacci(int n)
    {

        if (n < 1)
        {
            throw new IllegalArgumentException(String.format("Illegal argument: %d", n));
        }
        if (n < 3)
        {

            return 1;

        }
        else
        {

            int last = 2, lastButOne = 1;

            for (int i = 3; i < n; i++)
            {
                int tmp = last + lastButOne;
                lastButOne = last;
                last = tmp;
            }

            return last;

        }

    }

    private static String tryReadLine(BufferedReader br)
    {
        try
        {
            return br.readLine();
        }
        catch (IOException e)
        {
            System.err.println("Error while reading input.");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = tryReadLine(br);

        while (input != "q")
        {

            try
            {
                int n = Integer.parseInt(input);
                System.out.println(fibonacci(n));
            }
            catch (NumberFormatException e)
            {
                System.err.println("Please enter a non-negative, non-zero integer.");
            }
            catch (IllegalArgumentException e)
            {
                System.err.println("Please enter a non-negative, non-zero integer.");
            }

            input = tryReadLine(br);

        }

    }

}
