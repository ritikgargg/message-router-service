package midsem;

import java.io.IOException;
import java.util.logging.*;

public class Log {

    public Logger logCreator(String logFilePath) throws IOException {
        Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        FileHandler fh = new FileHandler(logFilePath, true);
        logr.addHandler(fh);
        fh.setFormatter(new SimpleFormatter());
        return logr;
    }
}
