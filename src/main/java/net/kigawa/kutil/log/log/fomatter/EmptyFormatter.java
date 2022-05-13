package net.kigawa.kutil.log.log.fomatter;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class EmptyFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return record.getMessage();
    }
}
