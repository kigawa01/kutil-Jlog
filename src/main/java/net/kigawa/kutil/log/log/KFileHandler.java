package net.kigawa.kutil.log.log;

import java.io.IOException;
import java.util.logging.LogRecord;

public class KFileHandler extends java.util.logging.FileHandler {
    public KFileHandler(String pattern, int limit, int count, boolean append) throws IOException, SecurityException {
        super(pattern, limit, count, append);
    }

    @Override
    public void publish(LogRecord record){
        super.publish(record);
        flush();
    }
}
