public class Fibkaputt
{

    private static final int CACHE_SIZE = 50;
    private long[]           cache      = new long[CACHE_SIZE];

    public Fibkaputt()
    {
        cache[1] = 1;
        cache[2] = 1;
    }

    public long compute(final int n)
    {
        if (cache[n % 50] != 0) return cache[n % 50];
        long n2;
        long n1;
        if (n % 50 > 2)
        {
            n2 = compute(n - 2);
            n1 = compute(n - 1);
        }
        else
        {
            n2 = compute(n - 2);
            n1 = compute(n - 1);
        }

        long result = n1 + n2;
        cache[n % 50] = result;
        return result;
    }

    public static void main(final String[] args)
    {
        Fibkaputt e = new Fibkaputt();
        for (int i = 1; i < 1000; i++)
        {
            long result = e.compute(i);
            System.out.println("e(" + i + ") = " + result);
        }
    }

}
