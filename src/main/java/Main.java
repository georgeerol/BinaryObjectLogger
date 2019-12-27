import binarylogger.BinaryLogFile;
import loggable.BinaryLoggable;
import loggable.Loggable;
import loggable.SystemLog;

import java.io.File;
import java.util.Iterator;
import java.util.Objects;

/**
 * Created by George Fouche on 12/26/19.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = Main.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("test")).getFile());

        BinaryLoggable loggable = new Loggable("I'm a log message");
        BinaryLoggable systemLog = new SystemLog("Computer", "I'm a super fast computer");

        /* Writing our BinaryLoggable Classes to test file */
        try (BinaryLogFile binaryLogFile = new BinaryLogFile(file)) {
            binaryLogFile.write(loggable);
            binaryLogFile.write(systemLog);

            /* Reading Loggable Class */
            Iterator<Loggable> loggableIterator = binaryLogFile.read(Loggable.class);
            while (loggableIterator.hasNext()) {
                Loggable message = loggableIterator.next();
                System.out.println(message.getLogMessage());
            }

            /* Reading SystemLog Class */
            Iterator<SystemLog> systemLogIterator = binaryLogFile.read(SystemLog.class);
            while (systemLogIterator.hasNext()) {
                SystemLog message = systemLogIterator.next();
                System.out.println("System type:" + message.getSystem());
                System.out.println(message.getLogMessage());
            }

        }

    }
}
