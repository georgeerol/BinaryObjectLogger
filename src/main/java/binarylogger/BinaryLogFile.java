package binarylogger;

import loggable.BinaryLoggable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * This class is the implementation of {@code BinaryLogger}.
 * It writes and reads {@code BinaryLoggable}s to the provided file.
 * Created by George Fouche on 12/23/19.
 */
public class BinaryLogFile<T extends BinaryLoggable> extends BinaryLogger<T> {

    private FileOutputStream fileOutputStream;
    private FileValidation fileValidation;
    private File file;

    /**
     * @param file - the file to read and write on
     */
    public BinaryLogFile(File file) {
        super(file);
        this.file = file;
        fileValidation = new FileValidation(file);

    }


    /**
     * @param loggable an instance of {@code loggable.BinaryLoggable} that needs to
     *                 be logged
     * @throws IOException - if an IO operation fails
     */
    @Override
    public void write(T loggable) throws IOException {
        if (fileValidation.isValid() && loggable != null) {
            this.fileOutputStream = new FileOutputStream(outputFile, true);
            String className = loggable.getClass().getCanonicalName();
            byte[] nameBytes = className.getBytes(StandardCharsets.UTF_8);
            byte[] data = loggable.toBytes();
            this.fileOutputStream.write(nameBytes);
            this.fileOutputStream.write("->".getBytes());
            this.fileOutputStream.write(data);
            this.fileOutputStream.write("\n".getBytes());
            this.fileOutputStream.flush();

        }

    }

    /**
     * @param clazz a class of the type T, clazz should have a public
     *              no-arg constructor
     * @return
     * @throws IOException - if the clazz doesn't exist, doesn't have a no-arg constructor
     */
    @Override
    public Iterator<T> read(Class<T> clazz) throws IOException {
        Iterator<T> classInfo;
        try (BinaryFileReader binaryFileReader = new BinaryFileReader(clazz.getCanonicalName(), file)) {
            classInfo = binaryFileReader.read();
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new IOException(e.getMessage());
        } catch (NoSuchMethodException e) {
            throw new IOException(clazz.getCanonicalName() + " does not have an empty constructor. " + e.getMessage());
        }
        return classInfo;
    }

    /**
     * Close the provided file output stream.
     *
     * @throws Exception- if it's unable to close the output stream
     */
    @Override
    public void close() throws Exception {
        if (fileOutputStream != null) this.fileOutputStream.close();
    }
}
