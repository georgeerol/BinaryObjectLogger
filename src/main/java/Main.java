import binarylogger.BinaryLogFile;
import loggable.BinaryLoggable;
import loggable.Loggable;
import loggable.SystemLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * This class is a Demo Main class.
 * One can use it to run the program via the command line or via the IDE play button
 * Created by George Fouche on 12/26/19.
 */
public class Main {


    /**
     * Preferred way is to run it via the IDE by providing a file.
     * The other way is to run it via the command line.
     * <p>
     * See Readme file on how to run it via the IDE and command line
     *
     * @param args - the file provided from the command line
     */
    public static void main(String[] args) {
        try {
            if (args.length == 0 || args.length > 1) {
                throw new FileNotFoundException("Please provide a file as an argument");
            }
            /* Run from the command line or IDE. File needs to be provided */
            File providedFile = new File(args[0]);
            if (!providedFile.exists()) {
                throw new FileNotFoundException("File does not exist");
            }
            demoRun(providedFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    private static void demoRun(File file) throws Exception {
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
