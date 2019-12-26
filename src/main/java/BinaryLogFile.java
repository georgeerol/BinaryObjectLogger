import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * A Binary log file stored data in binary format. That makes it quicker for writting log information.
 *
 * Binary log file only capture Data Changing information, it is also used for replication.
 * Created by George Fouche on 12/23/19.
 */
public class BinaryLogFile<T extends BinaryLoggable> extends BinaryLogger<T> {

    File file;

    public BinaryLogFile(File file) {
        super(file);

    }

    @Override
    void write(T loggable) throws IOException {

    }

    @Override
    Iterator<T> read(Class<T> clazz) throws IOException {
        return null;
    }

    @Override
    public void close() throws Exception {

    }

    public static void main(String[] args) {
        File file = new File(BinaryLogFile.class.getClassLoader().getResource("test").getFile());
        System.out.println(file.exists());
    }
}
