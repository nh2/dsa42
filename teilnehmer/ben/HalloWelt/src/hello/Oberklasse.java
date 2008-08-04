package hello;

public class Oberklasse extends Object
{

    public Oberklasse(String s)
    {
        // TODO Auto-generated constructor stub
    }

    protected int i = 1;

    public void offen()
    {

    }

    protected void geschuetzt()
    {

    }

    private void privat()
    {

    }

    void paket()
    {

    }

    public void abc(Oberklasse other)
    {
        other.privat();
        int a = other.i;
        new Abc();
    }

    public static void main(String[] args)
    {

    }

    private class Abc
    {

        public Abc()
        {
            // TODO Auto-generated constructor stub
        }
    }
}
