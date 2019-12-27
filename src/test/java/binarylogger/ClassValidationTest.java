package binarylogger;

import loggable.NotLoggable;
import loggable.SystemLog;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertTrue;

/**
 * Created by George Fouche on 12/26/19.
 */
public class ClassValidationTest {

    @Test
    public void testAValidClass() throws ClassNotFoundException {
        ClassValidation classValidation = new ClassValidation(SystemLog.class.getCanonicalName());
        assertTrue(classValidation.isValid());
    }


    @Test(expected = ClassNotFoundException.class)
    public void testAInvalidClass() throws ClassNotFoundException {
        ClassValidation classValidation = new ClassValidation("TheClass");
        classValidation.isValid();
    }

    @Test
    public void testAnInstanceOfBinaryLoggable() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ClassValidation classValidation = new ClassValidation(SystemLog.class.getCanonicalName());
        assertTrue(classValidation.isInstanceOfBinaryLoggable());
    }

    @Test(expected = IllegalStateException.class)
    public void testAnInstanceOfNotBinaryLoggable() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ClassValidation classValidation = new ClassValidation(NotLoggable.class.getCanonicalName());
        classValidation.isInstanceOfBinaryLoggable();
    }


}
