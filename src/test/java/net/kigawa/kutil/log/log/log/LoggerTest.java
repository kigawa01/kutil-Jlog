package net.kigawa.kutil.log.log.log;

import net.kigawa.kutil.kutil.app.Formatter;
import net.kigawa.kutil.log.log.LogSender;
import net.kigawa.kutil.log.log.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class LoggerTest implements LogSender {
    public LoggerTest() {
        fine("fine");
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("fine");
        logger.setLevel(Level.ALL);
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
        logger.info("info_logger");
        logger.fine("fine_logger");
        Logger.getInstance().fine("aaaaaaaa");
    }

    public static void main(String[] args) {

        java.util.logging.Logger.getLogger("").getHandlers()[0].setFormatter(new Formatter());
        new Logger("test", null, Level.ALL, new File(Paths.get("").toAbsolutePath().toFile(), "log")).enable();
        Logger.getInstance().info("aaa");
        Logger logger = Logger.getInstance();
        new Logger("test1", Logger.getInstance(), Level.ALL, new File(Paths.get("").toAbsolutePath().toFile(), "log1")).enable();
        Logger.getInstance().info("iii");
        Logger.getInstance().info("uuu");
        Logger.getInstance().info((Object) new String[]{
                "a", "b"
        });
        Logger.getInstance().info("");

        try {
            String ab = getNull();
            throw new IOException();
        } catch (Exception e) {
            Logger.getInstance().warning(e);
        }

        Scanner scanner = new Scanner(System.in);
        new LoggerTest();

        while (scanner.hasNext()) {
            String a = scanner.next();
            Logger.getInstance().info(a);
            if (a.equals("end")) break;
            if (a.equals("notify")) logger.notifyAll();
        }

        logger.disable();
        Logger.getInstance().disable();
    }

    public static String getNull() {
        return null;
    }
}
