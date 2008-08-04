package de.schelklingen2008.util;

import java.util.logging.Logger;

/**
 * A factory for creating Log instances conveniently. By using this class it is no longer necessary
 * to provide a class when creating a Log instance.
 * 
 * @author gwenz
 */
public class LoggerFactory
{

    /**
     * Private constructor since there should be no instances of this class.
     */
    private LoggerFactory()
    {
    }

    /**
     * Creates a commons logging Log instane. The log instance is initialized with the class calling
     * this method.
     * 
     * @return A Logger instance.
     */
    public static Logger create()
    {
        Throwable t = new Throwable();
        StackTraceElement directCaller = t.getStackTrace()[1];
        return Logger.getLogger(directCaller.getClassName());
    }

    public static Logger create(String name)
    {
        return Logger.getLogger(name);
    }
}
