package lars;

public class Fibonacci
{

    private int max;

    public Fibonacci()
    {
        max = 0;
    }

    public Fibonacci(int max)
    {
        this.max = max;
    }

    public void print()
    {
        print(max);
    }

    public void print(int max)
    {
        int n = 0;
        int nMinus1 = 1;
        int nMinus2 = 0;

        if (max <= 0)
        {
            throw new IllegalArgumentException("fibonacci value negative");
        }
        else
        {
            // first value is always 1
            System.out.println(1);
        }

        for (int i = 2; i <= max; i++)
        {
            n = nMinus1 + nMinus2;
            nMinus2 = nMinus1;
            nMinus1 = n;
            System.out.println(n);
        }
    }

    public int calculateRecursive()
    {
        return calculateRecursively(max);
    }

    public int calculateRecursively(int n)
    {
        if (n == 1 || n == 2)
        {
            return 1;
        }
        else
        {
            return calculateRecursively(n - 1) + calculateRecursively(n - 2);
        }

    }

    public static void main(String[] args)
    {
        Fibonacci fibonacci = new Fibonacci(10);

        fibonacci.print();
    }

}
