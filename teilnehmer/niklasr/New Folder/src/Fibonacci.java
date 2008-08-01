public class Fibonacci
{

    int i, j;

    public Fibonacci()
    {
        // TODO Auto-generated constructor stub
    }

    public int fibonacci(int n)
    {
        if (n == 1 || n == 2) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);

    }

    public static void main(String[] args)
    {
        Fibonacci e = new Fibonacci();
        System.out.println(e.fibonacci(10));
    }
}
