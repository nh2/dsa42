package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hello
{

    String hello;
    String name;

    public Hello()
    {
        name = "Hans";
    }

    public void hello(String a) throws IOException
    {
        String hello = "a";
        System.out.println(hello);
    }

    public void hello() throws IOException
    {
        System.out.println("Hallo Welt!!");
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
