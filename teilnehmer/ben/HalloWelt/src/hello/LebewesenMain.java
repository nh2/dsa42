package hello;

public class LebewesenMain
{

    public LebewesenMain()
    {
        Lebewesen l1 = new Affe();
        Lebewesen l2 = new Mensch();

        Affe a = (Affe) l1;
        a.huepfen();
    }
}
