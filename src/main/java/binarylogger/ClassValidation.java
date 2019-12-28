package binarylogger;

import loggable.BinaryLoggable;

import java.lang.reflect.InvocationTargetException;

/**
 * This class check if a class name exit and is an implementation of {@code BinaryLoggable}
 * Created by George Fouche on 12/25/19.
 */
public class ClassValidation {

    private String tClassName;

    public ClassValidation(String tClassName) {
        this.tClassName = tClassName;
    }


    /**
     * Check if a class name exist
     * @return - true if class name is valid
     * @throws ClassNotFoundException if class is not valid
     */
    public boolean isValid() throws ClassNotFoundException {
        try {
            Class.forName(tClassName);
            return true;
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        }
    }

    /**
     * Check if a class name is an implementation of {@code BinaryLoggable}
     *
     * @throws ClassNotFoundException    - if provided class does not exist
     * @throws NoSuchMethodException     - if provided class does not have a no-arg constructor
     * @throws IllegalAccessException    - if provided class  does not have access
     * @throws InvocationTargetException - if provided class  can't ve invoke
     * @throws InstantiationException    - if provided class can't be instantiate
     * @return- true if class is an implementation of {@code BinaryLoggable}
     */
    public boolean isInstanceOfBinaryLoggable() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> className = Class.forName(tClassName);
        Object constructClass = className.getConstructor().newInstance();
        if (!(constructClass instanceof BinaryLoggable)) {
            throw new IllegalStateException(tClassName + " does not implement loggable.BinaryLoggable");
        }
        return true;
    }

}
