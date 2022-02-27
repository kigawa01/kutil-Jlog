package net.kigawa.kutil.log.log;

import net.kigawa.kutil.kutil.file.Extension;
import net.kigawa.kutil.kutil.interfaces.Module;
import net.kigawa.kutil.kutil.string.StringUtil;
import net.kigawa.kutil.kutil.util.TaskStocker;
import net.kigawa.log.FileHandler;
import net.kigawa.log.Formatter;
import net.kigawa.log.LogSender;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class Logger extends java.util.logging.Logger implements LogSender, Module {
    private static Logger logger;
    private final TaskStocker stocker = new TaskStocker();
    private net.kigawa.log.FileHandler fileHandler;

    public Logger(String name, java.util.logging.Logger parentLogger, Level logLevel, File logDir, Handler... handlers) {
        super(
                getLoggerName(name, parentLogger),
                null
        );

        Path logDirPath = null;
        if (logDir != null) logDirPath = logDir.toPath();

        if (parentLogger == null) setParent(Logger.getLogger(""));
        else setParent(parentLogger);

        setLevel(logLevel);

        if (logDirPath != null) {
            logDirPath.toFile().mkdirs();
            Calendar calendar = Calendar.getInstance();
            StringBuffer logName = StringUtil.addYearToDate(new StringBuffer("log"), "-");
            File logFile = new File(logDirPath.toFile(), Extension.log.addExtension(logName.toString()));

            try {
                net.kigawa.log.FileHandler handler = new FileHandler(logFile.getAbsolutePath(), 1024 * 1024, 1, false);
                handler.setLevel(logLevel);
                addHandler(handler);
                handler.setFormatter(new net.kigawa.log.Formatter());
                fileHandler = handler;
            } catch (IOException e) {
                Logger.getInstance().warning(e);
            }

        }

        for (Handler handler : handlers) {
            addHandler(handler);
        }
    }

    private static String getLoggerName(String name, java.util.logging.Logger parentLogger) {
        String loggerName = name;
        if (parentLogger != null) loggerName = parentLogger.getName() + "." + name;
        return loggerName;
    }

    public static Logger getInstance() {
        if (logger == null) logger = new Logger("logger", null, null, null);
        return logger;
    }

    public static void setLogger(Logger logger) {
        Logger.logger = logger;
    }

    public void removeFileHandler() {
        removeHandler(fileHandler);
        fileHandler = null;
    }

    public void anSyncLog(Log log, Level level) {
        stocker.add(() -> log(log, level));
    }

    @Override
    public void log(Level level, String str) {
        anSyncLog(str, level);
    }

    public  void anSyncLog(Object o, Level level) {
        stocker.add(() -> log(o, level));
    }

    public synchronized void log(Object o, Level level) {
        if (o.getClass().isArray()) {
            for (Object o1 : (Object[]) o) {
                log(o1, level);
            }
            return;
        }
        if (o instanceof Throwable) {
            Throwable throwable = (Throwable) o;
            log(throwable.toString(), level);
            log(throwable.getStackTrace(), level);
            log(throwable.getSuppressed(), level);
            return;
        }
        if (o instanceof StackTraceElement) {
            StackTraceElement element = (StackTraceElement) o;
            log("\tat " + element, level);
            return;
        }
        super.log(level, o.toString());

    }

    @Override
    public void enable() {
        logger = this;
        for (Handler handler : getLogger("").getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                handler.setFormatter(new Formatter());
            }
        }
    }

    public synchronized void disable() {
        stocker.end();
    }

    public interface Log {
        @Override
        String toString();
    }
}
