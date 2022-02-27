package net.kigawa.kutil.log.log;

import net.kigawa.log.EmptyFormatter;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ResendLog extends Handler {
    private final Logger logger;
    private final Level level;

    public ResendLog(Logger logger, Level level) {
        this.logger = logger;
        this.level = level;
        setFormatter(new EmptyFormatter());
    }

    @Override
    public void publish(LogRecord record) {
        logger.log(level, getFormatter().format(record));
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }
}
