package binarylogger;

import loggable.BinaryLoggable;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by George Fouche on 12/25/19.
 */
public class BinaryFileReader<T extends BinaryLoggable> implements AutoCloseable {
    private String tClassName;
    private File file;
    private FileValidation fileValidation;
    private ClassValidation classValidation;
    private FileInputStream fileInputStream;

    public BinaryFileReader(String tClassName, File file) {
        this.tClassName = tClassName;
        this.file = file;
        this.fileValidation = new FileValidation(file);
        this.classValidation = new ClassValidation(tClassName);

    }

    public Iterator<T> read() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (fileValidation.isValid() && classValidation.isValid() && classValidation.isInstanceOfBinaryLoggable()) {
            fileInputStream = new FileInputStream(file);
            T binaryLoggableClass;
            BufferedReader br = null;
            try{
               br = new BufferedReader(new InputStreamReader(fileInputStream));
                byte[] data;
                String line;
                List<T> list = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    String[] str = line.split("->");
                    String className = str[0];
                    if (this.tClassName.equals(className)) {
                        data = str[1].getBytes();
                        Class<?> aClass = Class.forName(tClassName);
                        Object constructClass = aClass.getConstructor().newInstance();
                        binaryLoggableClass = (T) constructClass;
                        binaryLoggableClass.fromBytes(data);
                        list.add(binaryLoggableClass);
                    }
                }
                return list.iterator();
            }finally {
                br.close();
            }
        }
        return null;
    }

    @Override
    public void close() throws IOException {
        fileInputStream.close();


    }
}
