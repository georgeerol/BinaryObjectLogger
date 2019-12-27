import binarylogger.BinaryLogFile;
import loggable.BinaryLoggable;
import loggable.Loggable;

import java.io.File;
import java.util.Iterator;

/**
 * Created by George Fouche on 12/26/19.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = Main.class.getClassLoader();
        File file = new File(classLoader.getResource("test").getFile());
        BinaryLoggable loggable = new Loggable("I'm a log message");
        BinaryLogFile binaryLogFile = new BinaryLogFile(file);
        binaryLogFile.write(loggable);
        binaryLogFile.close();
        Iterator<Loggable> george = binaryLogFile.read(Loggable.class);
        while(george.hasNext()){
            Loggable message = george.next();
            System.out.println(message.getLogMessage());
        }

    }
}
