package binarylogger;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by George Fouche on 12/26/19.
 */
public class FileValidationTest {
    private File file;

    @Before
    public void setup() {
        ClassLoader classLoader = BinaryLogFileIntegrationTest.class.getClassLoader();
        file = new File(classLoader.getResource("test").getFile());
    }

    @Test
    public void testIfFileIsValid() throws IOException {
        FileValidation fileValidation = new FileValidation(file);
        assertTrue(fileValidation.isValid());

    }

    @Test(expected = IOException.class)
    public void testIfFileIsNotValid() throws IOException {
        FileValidation fileValidation = new FileValidation(new File(""));
        assertFalse(fileValidation.isValid());

    }


}
