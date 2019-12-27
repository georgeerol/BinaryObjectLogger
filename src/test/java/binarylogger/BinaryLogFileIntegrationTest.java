package binarylogger;

import loggable.*;
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
        ClassLoader classLoader = BinaryLogFileIntegrationTest.class.getClassLoader();
        file = new File(classLoader.getResource("test").getFile());
    }


    @Test
    public void testBinaryLoggableWritingAndReadingFromFile() throws Exception {
        BinaryLoggable loggable = new Loggable("I'm a log message");
        BinaryLogFile binaryLogFile = new BinaryLogFile(file);
        binaryLogFile.write(loggable);
        Iterator<BinaryLoggable> binaryLoggableIterator = binaryLogFile.read(Loggable.class);
        binaryLogFile.close();
        Loggable message = (Loggable) binaryLoggableIterator.next();
        assertEquals(message.getLogMessage(), ((Loggable) loggable).getLogMessage());
    }

    @Test
    public void testDifferentBinaryLoggableClassesWritingAndReadingFromFile() throws Exception {
        BinaryLoggable loggable = new Loggable("I'm a log message");
        BinaryLoggable  systemLog = new SystemLog("Computer","I'm the fastest computer");
        BinaryLogFile binaryLogFile = new BinaryLogFile(file);
        binaryLogFile.write(loggable);
        binaryLogFile.write(systemLog);


        /* Loggable Class */
        Iterator<BinaryLoggable> binaryLoggableIterator = binaryLogFile.read(Loggable.class);
        Loggable message = (Loggable) binaryLoggableIterator.next();
        assertEquals(message.getLogMessage(), ((Loggable) loggable).getLogMessage());

       /* SystemLog Class */
        Iterator<BinaryLoggable> systemLogIterator = binaryLogFile.read(SystemLog.class);
        binaryLogFile.close();
        SystemLog systemMessage = (SystemLog) systemLogIterator.next();

        assertEquals(systemMessage.getSystem(), ((SystemLog) systemLog).getSystem());
        assertEquals(systemMessage.getLogMessage(),((SystemLog) systemLog).getLogMessage());
        binaryLogFile.close();



    }

    @Test
    public void testReadAClassThatDoesNotImplementBinaryLoggable() throws Exception {
        String expectedMessage = "loggable.NotLoggable does not implement loggable.BinaryLoggable";
        BinaryLogFile binaryLogFile = new BinaryLogFile(file);
        try {
            binaryLogFile.read(NotLoggable.class);
        } catch (IllegalStateException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }


    @Test
    public void testWriteOnANoNameFile() {
        String expectedMessage = "Unable to create file. No such file or directory";
        BinaryLoggable loggable = new Loggable("I'm a log message");
        BinaryLogFile binaryLogFile = new BinaryLogFile(new File(""));
        try {
            binaryLogFile.write(loggable);
        } catch (IOException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }


    @Test
    public void testReadOnANoNameFile() {
        String expectedMessage = "Unable to create file. No such file or directory";
        BinaryLogFile binaryLogFile = new BinaryLogFile(new File(""));
        try {
            binaryLogFile.read(Loggable.class);
        } catch (IOException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }


    @Test
    public void testReadWithNoEmptyConstructorLoggable() {
        String expectedMessage = "loggable.NoEmptyConstructorLoggable does not have an empty constructor. " +
                "loggable.NoEmptyConstructorLoggable.<init>()";
        BinaryLogFile binaryLogFile = new BinaryLogFile(file);
        try {
            binaryLogFile.read(NoEmptyConstructorLoggable.class);
        } catch (IOException e) {
            assertEquals(expectedMessage, e.getMessage());
        }


    }

    @After
    public void cleanup() throws IOException {
        file.delete();
        file.createNewFile();
    }
}
