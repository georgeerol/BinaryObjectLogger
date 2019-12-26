
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by George Fouche on 12/25/19.
 */
public class BinaryFileReader<T extends BinaryLoggable>  implements Closeable {
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

    public Iterator<T>  read() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (fileValidation.isValid() && classValidation.isValid()) {
            fileInputStream = new FileInputStream(file);

            T candidate;
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            byte[] data;
            String line;
            List<T> list = new ArrayList<>();
            while((line = br.readLine()) !=null){
                String[] str = line.split(":");
                String className = str[0];
                if(this.tClassName.equals(className)){
                    data = str[1].getBytes();
                    Class<?> restoredClass = Class.forName(className);
                    Object restored = restoredClass.getConstructor().newInstance();
                    candidate = (T) restored;
                    candidate.fromBytes(data);
                    list.add(candidate);
                }

            }
            Iterator<T> iterator = list.iterator();
            return  iterator;
        }
        return null;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        File file = new File("george");
        System.out.println(file.exists());
        BinaryFileReader binaryFileReader = new BinaryFileReader("Pet", file);
        binaryFileReader.read();
    }





    @Override
    public void close() throws IOException {


    }
}
