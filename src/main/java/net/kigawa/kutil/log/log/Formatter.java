package net.kigawa.kutil.log.log;

import java.util.Calendar;
import java.util.logging.LogRecord;

public class Formatter extends java.util.logging.Formatter {
    private final Calendar calendar = Calendar.getInstance();
    @Override
    public String format(LogRecord record) {
        StringBuffer sb = new StringBuffer();
        calendar.setTimeInMillis(record.getMillis());
        sb.append(Calendar.MONTH).append("-").append(Calendar.DAY_OF_MONTH).append("-").append(Calendar.HOUR_OF_DAY)
                .append("\n").append("-").append(Calendar.MINUTE).append("-").append(Calendar.SECOND).append("[")
                .append(record.getLevel().getName()).append("] |").append(record.getMessage());
        return sb.toString();
    }
}
