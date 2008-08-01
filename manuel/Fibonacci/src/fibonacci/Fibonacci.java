package fibonacci;

public class Fibonacci
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

}
