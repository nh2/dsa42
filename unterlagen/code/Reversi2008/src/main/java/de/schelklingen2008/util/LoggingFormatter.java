package de.schelklingen2008.util;

import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import org.apache.commons.lang.exception.ExceptionUtils;

public class LoggingFormatter extends Formatter
{

    private static final String LINE_SEP      = System.getProperty("line.separator");

    private MessageFormat       dateFormatter = new MessageFormat("{0,time}");

    /**
     * Formats the given LogRecord.
     */
    @Override
    public synchronized String format(LogRecord record)
    {
        StringBuffer sb = new StringBuffer();

        // time
        Date date = new Date();
        date.setTime(record.getMillis());
        Object[] args = new Object[1];
        args[0] = date;
        StringBuffer text = new StringBuffer();
        dateFormatter.format(args, text, null);
        sb.append(text);
        sb.append(" ");

        // level
        sb.append(record.getLevel().getLocalizedName());
        sb.append(" ");

        // class and method
        if (record.getSourceClassName() != null)
            sb.append(record.getSourceClassName());
        else
            sb.append(record.getLoggerName());
        if (record.getSourceMethodName() != null)
        {
            sb.append(".");
            sb.append(record.getSourceMethodName());
            sb.append("()");
        }
        sb.append(": ");

        // message
        String message = formatMessage(record);
        sb.append(message);
        sb.append(LINE_SEP);

        // stack trace
        Throwable t = record.getThrown();
        if (t != null) sb.append(ExceptionUtils.getStackTrace(t));

        return sb.toString();
    }
}
