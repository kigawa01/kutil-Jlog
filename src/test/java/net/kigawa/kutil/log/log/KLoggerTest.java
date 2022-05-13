package net.kigawa.kutil.log.log;

import net.kigawa.kutil.kutil.KutilArray;
import net.kigawa.kutil.kutil.KutilFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.*;

class KLoggerTest
{
    private static KLogger logger;
    private static LogRecord publishedRecord;

    @Test
    void enable()
    {
        createLogger(null, Level.INFO, null);
        removeLogger();
        createLogger(Logger.getLogger(""), Level.INFO, null);
        removeLogger();
        createLogger(null, Level.INFO, KutilFile.getRelativeFile("test"));
        removeLogger();
    }

    private void createLogger(Logger parent, Level level, File fileDir)
    {
        logger = new KLogger("test", parent, level, fileDir, new Handler()
        {
            @Override
            public void publish(LogRecord record)
            {
                KLoggerTest.publishedRecord = record;
            }

            @Override
            public void flush()
            {

            }

            @Override
            public void close() throws SecurityException
            {

            }
        });
        logger.enable();
    }

    private void removeLogger()
    {
        logger.disable();
    }

    @Test
    void log()
    {
        createLogger(null, Level.INFO, null);
        logger.log(Level.INFO, "test");
        Assertions.assertEquals("test", publishedRecord.getMessage());
        Assertions.assertEquals(Level.INFO, publishedRecord.getLevel());
        publishedRecord = null;

        logger.log(Level.FINE, "test");
        Assertions.assertNull(publishedRecord);
        publishedRecord = null;
        removeLogger();

        createLogger(null, Level.FINE, null);
        logger.log(Level.FINE, "test");
        Assertions.assertEquals("test", publishedRecord.getMessage());
        Assertions.assertEquals(Level.FINE, publishedRecord.getLevel());
        publishedRecord = null;
        removeLogger();
    }

    @Test
    void removeFileHandler()
    {
        createLogger(null, Level.INFO, KutilFile.getRelativeFile("test"));
        FileHandler fileHandler = null;
        for (Handler handler : logger.getHandlers())
            if (handler instanceof FileHandler) fileHandler = (FileHandler) handler;
        logger.removeFileHandler();
        Assertions.assertFalse(KutilArray.contain(logger.getHandlers(), fileHandler));
        removeLogger();
    }
}