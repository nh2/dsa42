package fibonacci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo
{

    public static int berechneFibonacci(int i)
    {
        if (i == 1 || i == 2)
            return 1;
        else
            return berechneFibonacci(i - 1) + berechneFibonacci(i - 2);
    }

    public static void main(String[] args)
    {
        for (int i = 1; i < 10; i++)
            System.out.println(berechneFibonacci(i));

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String inputZahl;
        System.out.println("Berechne Fibonacci-Zahl von: ");
        try
        {
            inputZahl = in.readLine();
        }
        catch (IOException e)
        {
            System.out.println("Fehler bei der Eingabe");
            return;
        }
        System.out.println(berechneFibonacci(Integer.parseInt(inputZahl)));
    }

}
