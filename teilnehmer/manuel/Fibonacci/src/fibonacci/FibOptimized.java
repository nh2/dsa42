package fibonacci;

import java.util.ArrayList;

public class FibOptimized
{

    private static final int INITIAL_CACHE_CAPACITY = 50;

    private static final int OUTPUT_LIMIT           = 50;

    private ArrayList<Long>  cache                  = new ArrayList<Long>(INITIAL_CACHE_CAPACITY);

    public FibOptimized()
    {
        cache.add(1L);
        cache.add(1L);
    }

    public long compute(final int n)
    {

        if (n >= cache.size())
        {

            for (int i = cache.size() - 1; i < n; i++)
            {
                cache.add(cache.get(n - 2) + cache.get(n - 1));
            }

        }

        return cache.get(n - 1);
    }

    public static void main(final String[] args)
    {
        FibOptimized e = new FibOptimized();
        for (int i = 1; i <= OUTPUT_LIMIT; i++)
        {
            long result = e.compute(i);
            System.out.println("e(" + i + ") = " + result);
        }
    }

}
