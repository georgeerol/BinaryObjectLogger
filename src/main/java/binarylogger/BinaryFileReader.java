package binarylogger;

import loggable.BinaryLoggable;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class reads {@code BinaryLoggable}s from a provided file.
 * Created by George Fouche on 12/25/19.
 */
public class BinaryFileReader<T extends BinaryLoggable> implements AutoCloseable {
    private String tClassName;
    private File file;
    private FileValidation fileValidation;
    private ClassValidation classValidation;
    private FileInputStream fileInputStream;

    /**
     * @param tClassName-the {@code BinaryLoggable} class name
     * @param file           - the provided file
     */
    public BinaryFileReader(String tClassName, File file) {
        this.tClassName = tClassName;
        this.file = file;
        this.fileValidation = new FileValidation(file);
        this.classValidation = new ClassValidation(tClassName);

    }

    /**
     * Read {@code BinaryLoggable}s  class data from the provided file
     *
     * @return - a list iterator of the provided class
     * @throws IOException               - if any IO operation fails
     * @throws ClassNotFoundException    - if provided class does not exist
     * @throws NoSuchMethodException     - if provided class does not have a no-arg constructor
     * @throws IllegalAccessException    - if provided class  does not have access
     * @throws InvocationTargetException -if provided class  can't ve invoke
     * @throws InstantiationException    - if provided class can't be instantiate
     */
    public Iterator<T> read() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        if (fileValidation.isValid() && classValidation.isValid() && classValidation.isInstanceOfBinaryLoggable()) {
            fileInputStream = new FileInputStream(file);
            T binaryLoggableClass;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))) {
                byte[] data;
                String line;
                List<T> list = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    String[] str = line.split("->");
                    String className = str[0];
                    if (this.tClassName.equals(className)) {
                        data = (str.length > 1) ? str[1].getBytes() : "".getBytes();
                        Class<?> aClass = Class.forName(tClassName);
                        Object constructClass = aClass.getConstructor().newInstance();
                        binaryLoggableClass = (T) constructClass;
                        binaryLoggableClass.fromBytes(data);
                        list.add(binaryLoggableClass);
                    }
                }
                return list.iterator();
            }
        }
        return null;
    }

    /**
     * Close the provided file input stream.
     *
     * @throws IOException - if it's unable to close the input stream
     */
    @Override
    public void close() throws IOException {
        if (fileInputStream != null) fileInputStream.close();


    }
}
