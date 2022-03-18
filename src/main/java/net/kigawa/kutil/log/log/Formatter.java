package net.kigawa.kutil.log.log;

import java.util.Calendar;
import java.util.logging.LogRecord;

public class Formatter extends java.util.logging.Formatter {
    private final Calendar calendar = Calendar.getInstance();

    @Override
    public String format(LogRecord record) {
        StringBuffer sb = new StringBuffer();
        calendar.setTimeInMillis(record.getMillis());
        sb.append(calendar.get(Calendar.MONTH)).append("-").append(calendar.get(Calendar.DAY_OF_MONTH))
                .append("-").append(calendar.get(Calendar.HOUR_OF_DAY)).append("-")
                .append(calendar.get(Calendar.MINUTE)).append("-").append(calendar.get(Calendar.SECOND)).append("[")
                .append(record.getLevel().getName()).append("] |").append(record.getMessage()).append("\n");
        return sb.toString();
    }
}
