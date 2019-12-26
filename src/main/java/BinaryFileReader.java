
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

/**
 * Created by George Fouche on 12/25/19.
 */
public class BinaryFileReader<T extends BinaryLoggable> {
    private final static int TWO_INTEGERS = Integer.BYTES * 2;
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

    public T read() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (fileValidation.isValid() && classValidation.isValid()) {
            fileInputStream = new FileInputStream(file);

        }

        return null;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        File file = new File("george");
        System.out.println(file.exists());
        BinaryFileReader binaryFileReader = new BinaryFileReader("Pet",file);
        binaryFileReader.read();
    }
}
