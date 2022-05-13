package net.kigawa.kutil.log.log;

import java.io.File;
import java.util.logging.Handler;
import java.util.logging.Level;

/**
 * @deprecated use KLogger
 */
public class Logger extends KLogger
{
    public Logger(String name, java.util.logging.Logger parentLogger, Level logLevel, File logDir, Handler... handlers)
    {
        super(name, parentLogger, logLevel, logDir, handlers);
    }
}
