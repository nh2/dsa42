public class Fibonacci
{

    public Fibonacci()
    {

    }

    public static void main(String[] args)
    {
        System.out.println(berechnen(4));

    }

    private static int berechnen(int n)
    {

        if (n == 1 || n == 2)
        {
            return 1;
        }
        else
        {
            return berechnen(n - 1) + berechnen(n - 2);
        }
    }
}
