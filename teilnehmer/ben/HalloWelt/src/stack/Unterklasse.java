package stack;

import hello.Oberklasse;

public class Unterklasse extends Oberklasse
{

    public Unterklasse()
    {
        this("a");
    }

    public Unterklasse(String s)
    {
        super(s);
    }

    private void abc()
    {
        geschuetzt();
        int a = i;
    }

    @Override
    protected void geschuetzt()
    {
        System.out.println("a");
        super.geschuetzt();
        System.out.println("b");
    }

    public static void main(String[] args)
    {

    }
}
