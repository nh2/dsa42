package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hello
{

    public Hello()
    {
    }

    public void hello() throws IOException
    {
        System.out.println("Hallo tolle Welt!!");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        System.out.println("Hallo " + name);
    }

    public static void main(String[] args) throws IOException
    {
        Hello hello = new Hello();
        hello.hello();
    }
}
