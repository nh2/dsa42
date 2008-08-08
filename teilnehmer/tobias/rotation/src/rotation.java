public class rotation
{

    public static void main(String[] args)
    {
        System.out.println("Hallo Welt");
        System.out.println(5 + 4);
        int ergebnis = 5 + 4;
        System.out.println(ergebnis);
        ergebnis = 5 + 5;
        System.out.println(ergebnis);
        ergebnis = ergebnis + 5;
        System.out.println(ergebnis);
        sagHallo();
        gebeZahlenAus(10);
        sagHallo2();
        System.out.println(quadrat(25));
        System.out.println(potenz(2, 5));
        System.out.println(max(5, 4));
        System.out.println(max(6, 3));
        System.out.println(max(8, 8));
    }

    public static void sagHallo()
    {
        String name = javax.swing.JOptionPane.showInputDialog("Name?");
        System.out.println("Hallo " + name);
    }

    public static void gebeZahlenAus(int max)
    {
        for (int i = 0; i <= max; i++)
        {
            System.out.println(i);
        }
    }

    public static void sagHallo2()
    {
        String name = javax.swing.JOptionPane.showInputDialog("Name?");
        System.out.println("Hallo " + name);
        if (name.equals("Chuck Norris"))
        {
            System.out.println("Oh mein Gott!! Was für eine Ehre!!");
        }
        else
        {
            System.out.println("Supi!");
        }
    }

    public static int quadrat(int basis)
    {
        return basis * basis;
    }

    public static int potenz(int basis, int exponent)
    {
        int ergebnis = 1;
        for (int i = 0; i < exponent; i++)
        {
            ergebnis = ergebnis * basis;
        }
        return ergebnis;
    }

    public static int max(int z1, int z2)
    {
        if (z1 > z2)
        {
            return z1;
        }
        if (z2 > z1)
        {
            return z2;
        }
        return z1;
    }
}
