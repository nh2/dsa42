package fibonacci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{

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
                System.out.println(Fibonacci.fibonacci(n));
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
