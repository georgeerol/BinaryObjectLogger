package binarylogger;

import loggable.BinaryLoggable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * A Binary log file stored data in binary format. That makes it quicker for writting log information.
 * <p>
 * Binary log file only capture Data Changing information, it is also used for replication.
 * Created by George Fouche on 12/23/19.
 */
public class BinaryLogFile<T extends BinaryLoggable> extends BinaryLogger<T> {

    private FileOutputStream fileOutputStream;
    private FileValidation fileValidation;
    private File file;

    public BinaryLogFile(File file) {
        super(file);
        this.file = file;
        fileValidation = new FileValidation(file);

    }


    @Override
    public void write(T loggable) throws IOException {
        if (fileValidation.isValid()) {
            if (loggable != null) {
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

    }


    @Override
    public Iterator<T> read(Class<T> clazz) throws IOException {
        Iterator<T> classInfo;
        BinaryFileReader binaryFileReader = new BinaryFileReader(clazz.getCanonicalName(), file);
        try {
            classInfo = binaryFileReader.read();
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new IOException(e.getMessage());
        } catch (NoSuchMethodException e) {
            throw new IOException(clazz.getCanonicalName() + " does not have an empty constructor. " + e.getMessage());
        }
        return classInfo;
    }

    @Override
    public void close() throws Exception {
        this.fileOutputStream.close();


    }
}
