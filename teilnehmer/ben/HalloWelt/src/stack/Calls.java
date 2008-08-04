package stack;

public class Calls
{

    public void test()
    {
        // String[][] aa = { { "a" }, { "c", "d" }, { "c", "d", "d" } };
        String[][] aa = new String[5][];
        for (int i = 0; i < aa.length; i++)
        {
            aa[i] = new String[] { "a", "b" };
            System.out.println(aa[i].length);
        }
    }

    public static void main(String[] args)
    {
        Calls calls = new Calls();
        calls.test();
    }
}
