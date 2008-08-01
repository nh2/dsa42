public class Fibonacci
{

    int hilf;

    public void berechnen(int zahl1, int zahl2, int i)
    {

        hilf = zahl1 + zahl2;
        System.out.print(", " + hilf);
        zahl1 = zahl2;
        zahl2 = hilf;
        if (i == 20)
        {
            System.exit(0);
        }
        berechnen(zahl1, zahl2, (i + 1));

    }

    public static void main(String[] args)
    {
        Fibonacci fib = new Fibonacci();
        System.out.print("1, 1");
        fib.berechnen(1, 1, 0);
    }
}
