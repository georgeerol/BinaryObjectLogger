package binarylogger;

import loggable.BinaryLoggable;
import loggable.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by George Fouche on 12/26/19.
 */
public class BinaryLogFileIntegrationTest {
    private File file;

    @Before
    public void setup() {
        this.file = new File("george");
    }


    @Test
    public void test() throws IOException {
        BinaryLoggable loggable = new Logger("I'm a log message");
        BinaryLogFile binaryLogFile = new BinaryLogFile(file);
        binaryLogFile.write(loggable);
        Iterator<BinaryLoggable> binaryLoggableIterator = binaryLogFile.read(Logger.class);
        Logger message = (Logger) binaryLoggableIterator.next();
        assertEquals(message.getLogMessage(), ((Logger) loggable).getLogMessage());
    }

    


    @After
    public void cleanup() throws IOException {
        file.delete();
        file.createNewFile();
    }
}
