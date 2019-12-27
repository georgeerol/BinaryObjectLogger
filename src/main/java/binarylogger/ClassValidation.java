package binarylogger;

import loggable.BinaryLoggable;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by George Fouche on 12/25/19.
 */
public class ClassValidation {

    private String tClassName;

    public ClassValidation(String tClassName) {
        this.tClassName = tClassName;
    }


    /**
     * @return
     */
    public boolean isValid() throws ClassNotFoundException {
        try {
            Class.forName(tClassName);
            return true;
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        }
    }

    public boolean isInstanceOfBinaryLoggable() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> className = Class.forName(tClassName);
        Object constructClass = className.getConstructor().newInstance();
        if (!(constructClass instanceof BinaryLoggable)) {
            throw new IllegalStateException(tClassName + " does not implement loggable.BinaryLoggable");
        }

        return true;
    }

}
