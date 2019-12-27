package binarylogger;

import binarylogger.BinaryFileReader;
import binarylogger.BinaryLogFile;
import loggable.Loggable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by George Fouche on 12/26/19.
 */
public class BinaryFileReaderTest {

    private File file;
    Loggable loggable;

    @Before
    public void setup() throws Exception {
        ClassLoader classLoader = BinaryLogFileIntegrationTest.class.getClassLoader();
        file = new File(classLoader.getResource("test").getFile());
        loggable = new Loggable("I'm a log message");
        BinaryLogFile binaryLogFile = new BinaryLogFile(file);
        binaryLogFile.write(loggable);
        binaryLogFile.close();
    }


    @Test
    public void testReadWithCorrectClassName() throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        String className = Loggable.class.getCanonicalName();
        BinaryFileReader binaryFileReader = new BinaryFileReader(className, file);
        Iterator data = binaryFileReader.read();
        Loggable message = (Loggable) data.next();
        assertEquals(message.getLogMessage(), loggable.getLogMessage());

    }

    @Test(expected = ClassNotFoundException.class)
    public void testReadWithIncorrectClassName() throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        BinaryFileReader binaryFileReader = new BinaryFileReader("ImClassy", file);
        binaryFileReader.read();
    }


    @Test(expected = IOException.class)
    public void testReadWithIncorrectFile() throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        String className = Loggable.class.getCanonicalName();
        BinaryFileReader binaryFileReader = new BinaryFileReader(className, new File(""));
        binaryFileReader.read();
    }



    @After
    public void cleanup() throws IOException {
        file.delete();
        file.createNewFile();
    }

}
