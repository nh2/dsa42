package exceptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Exceptions
{

    public static void main(String[] args)
    {
        test();
    }

    private static void test()
    {
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(new File("fdsfdsa"));

            // dkjshfksa

        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(new RuntimeException("Konnte Bild nicht laden.", e));
        }
        finally
        {
            try
            {
                if (fis != null) fis.close();
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }

        // dhskfds
    }
}
