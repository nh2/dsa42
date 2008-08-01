package fibonacci;

public class Fibonacci
{

    public Fibonacci()
    {
        // TODO Auto-generated constructor stub
    }

    private int calculate(int k)
    {
        // TODO Auto-generated method stub
        if (k == 1 || k == 2)
        {
            return 1;
        }
        else
        {
            return calculate(k - 1) + calculate(k - 2);
        }

    }

    private void print(int n)
    {
        if (n <= 0)
        {
            throw new IllegalArgumentExeption("Keine negativen Zahlen!");
        }
        else
        {
            System.out.println(calculate(n));
        }
    }

    public static void main(String[] args)
    {
        Fibonacci fibonacci = new Fibonacci();
        fibonacci.print(30);
    }
}
