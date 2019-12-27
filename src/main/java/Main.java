import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by George Fouche on 12/26/19.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("george");
        BinaryLoggable loggable = new Logger("I'm a log message");
        BinaryLogFile binaryLogFile = new BinaryLogFile(file);
        binaryLogFile.write(loggable);
        Iterator<Logger> george = binaryLogFile.read(Logger.class);
        while(george.hasNext()){
            Logger message = george.next();
            System.out.println(message.getLogMessage());
        }

    }
}
