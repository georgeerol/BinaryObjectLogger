package binarylogger;

import java.io.File;
import java.io.IOException;


/**
 * This class check the provided file exist or can be created
 * Created by George Fouche on 12/25/19.
 */
public class FileValidation {

    private File file;

    public FileValidation(File file) {
        this.file = file;
    }

    public boolean isValid() throws IOException {
        try {
            if (!file.exists()) return file.createNewFile();
        } catch (IOException e) {
            throw new IOException("The file does not exist and can't be created. " + e.getMessage(), e);
        }
        return true;
    }
}
