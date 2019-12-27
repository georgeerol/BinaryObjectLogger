package binarylogger;

import java.io.File;
import java.io.IOException;


/**
 * Created by George Fouche on 12/25/19.
 */
public class FileValidation {

    private File file;

    public FileValidation(File file) {
        this.file = file;
    }

    public boolean isValid() throws IOException {
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            throw new IOException("Unable to create file. " + e.getMessage(), e);
        }
        return true;
    }
}
