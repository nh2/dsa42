package hello;

public class Mensch implements Lebewesen
{

    int alter;

    public void altern()
    {
        alter += 1;
    }

    public int getAlter()
    {
        return alter;
    }

}
