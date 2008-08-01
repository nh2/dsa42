public class fibo
{

    public static void main(String[] args)
    {
        System.out.println(gibFiboaus(1));
        gibAlleFiboaus(50);
    }

    public static int gibFiboaus(int i)
    {
        if (i == 1 || i == 2)
            return 1;
        else
            return gibFiboaus(i - 1) + gibFiboaus(i - 2);

    }

    public static void gibAlleFiboaus(int z)
    {
        // for (int i = z; i > 0; i--)
        // {
        // System.out.println(gibFiboaus(i));
        // }
        if (z < 1) return;
        System.out.println(gibFiboaus(z));
        gibAlleFiboaus(--z);
    }

}
