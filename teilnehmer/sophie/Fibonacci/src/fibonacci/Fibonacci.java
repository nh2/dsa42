package fibonacci;

public class Fibonacci
{

    int a, b;

    public Fibonacci()
    {
        a = 1;
        b = 1;
    }

    public int fibonacci(int n)
    {
        int temp = 0;

        for (int i = 0; i < n; i++)
        {
            temp = a + b;
            a = b;
            b = temp;
        }

        return temp;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        Fibonacci f = new Fibonacci();
        System.out.println(f.fibonacci(10));

    }

}
