package Writers;

import org.apache.log4j.Logger;

/**
 * Created by GameMonkey on 13-01-2016.
 */
public class Log4jUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static Logger log = Logger.getRootLogger();

    public void uncaughtException(Thread t, Throwable ex) {
        log.error("Uncaught exception in thread: " + t.getName(), ex);
    }

}
