package net.kigawa.kutil.log.log;

import java.io.IOException;
import java.util.logging.LogRecord;
/**
 * @deprecated use KFileHandler
 */
public class FileHandler extends KFileHandler {
    public FileHandler(String pattern, int limit, int count, boolean append) throws IOException, SecurityException
    {
        super(pattern, limit, count, append);
    }
}
