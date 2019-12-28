import binarylogger.BinaryLogFile;
import loggable.BinaryLoggable;
import loggable.Loggable;
import loggable.SystemLog;

import java.io.File;
import java.util.Iterator;
import java.util.Objects;

/**
 * This class is a Demo Main class.
 * One can use it to run the program via the command line or via the IDE play button
 * Created by George Fouche on 12/26/19.
 */
public class Main {


    /**
     * Run the program via command line or via the IDE play button
     *
     * @param args - the file provided from the command line
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            /* Run from the command line. File needs to be provided */
            File providedFile = new File(args[0]);
            if (providedFile.exists()) {
                try { demoRunFromCommandLine(providedFile); } catch (Exception e) {
                    System.out.println(e.getMessage()); }
            }
        } else {
            /* Preferred Way: Run from this Main class by pressing Play from the IDE */
            try { demoRun(); } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void demoRunFromCommandLine(File file) throws Exception {
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

    public static void demoRun() throws Exception {
        ClassLoader classLoader = Main.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("test")).getFile());
        demoRunFromCommandLine(file);
    }
}
