package Application;

public class Berechnung
{

    public int result;

    public Berechnung()
    {
        result = 0;
    }

    public int fib(int number)
    {
        if (number == 1 || number == 2)
        {
            return result = 1;
        }
        else
        {
            return result = fib(number - 1) + fib(number - 2);
        }
    }

    public static void main(String[] args)
    {
        Berechnung ausgabe = new Berechnung();
        ausgabe.fib(10);

        System.out.println(ausgabe.result);
    }
}
