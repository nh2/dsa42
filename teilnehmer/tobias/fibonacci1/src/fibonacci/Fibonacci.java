package fibonacci;

public class Fibonacci
{

    private static final int CACHE_SIZE = 100;

    private long[]           cache      = new long[CACHE_SIZE];

    public Fibonacci()
    {
        cache[0] = 1;
        cache[1] = 1;
    }

    public long compute(final int n)
    {
        if (cache[n - 1] != 0) return cache[n - 1];

        long n2 = compute(n - 2);
        long n1 = compute(n - 1);
        long result = n1 + n2;
        cache[n - 1] = result;
        return result;
    }

    public static void main(final String[] args)
    {
        Fibonacci e = new Fibonacci();
        for (int i = 1; i < 90; i++)
        {
            long result = e.compute(i);
            System.out.println("e(" + (i + 1) + ") = " + result);
        }
    }

}
