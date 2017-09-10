package a01_EckelExample;

import java.util.logging.*;
import java.io.*;

class LoggingException extends Exception {
    private static Logger logger =
            Logger.getLogger("=========XxX============");
    LoggingException() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }
}

public class LoggingExceptions {
    public static void main(String[] args) {
        try {
            throw new LoggingException();
        } catch(LoggingException e) {
            System.err.println("Caught1 " + e);
        }
        try {
            throw new LoggingException();
        } catch(LoggingException e) {
            System.err.println("Caught2 " + e);
        }
    }
}
