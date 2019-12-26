import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * A Binary log file stored data in binary format. That makes it quicker for writting log information.
 * <p>
 * Binary log file only capture Data Changing information, it is also used for replication.
 * Created by George Fouche on 12/23/19.
 */
public class BinaryLogFile<T extends BinaryLoggable> extends BinaryLogger<T> {

    private FileOutputStream fileOutputStream;
    protected long position = 0L;

    FileValidation fileValidation;
    File file;

    public BinaryLogFile(File file) {
        super(file);
        this.file = file;
        fileValidation = new FileValidation(file);

    }


    @Override
    void write(T loggable) throws IOException {
        if (fileValidation.isValid()) {
            if (loggable != null) {
                this.fileOutputStream = new FileOutputStream(outputFile, true);
                String className = loggable.getClass().getCanonicalName();
                int twoIntegers= Integer.BYTES *2;
                byte[] twoIntegersBytes = new byte[twoIntegers];
                byte[] classNameBytes = className.getBytes();
                byte[] dataBytes = loggable.toBytes();
                this.fileOutputStream.write(twoIntegersBytes);
                this.fileOutputStream.write(classNameBytes);
                this.fileOutputStream.write(dataBytes);
            }
        }

    }


    @Override
    Iterator<T> read(Class<T> clazz) throws IOException {
        return null;
    }

    @Override
    public void close() throws Exception {


    }

    public static void main(String[] args) throws IOException {
//        File file = new File(BinaryLogFile.class.getClassLoader().getResource("test").getFile());
        File file = new File("george");
        System.out.println(file.exists());
        BinaryLoggable loggable = new Pet("George");
        BinaryLogFile binaryLogFile = new BinaryLogFile(file);
        binaryLogFile.write(loggable);

    }
}
