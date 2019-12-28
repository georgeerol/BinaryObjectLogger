package binarylogger;

import java.io.File;
import java.io.IOException;


/**
 * This class check the provided file exist or can be created.
 * Created by George Fouche on 12/25/19.
 */
public class FileValidation {

    private File file;

    /**
     * @param file - the provided file
     */
    public FileValidation(File file) {
        this.file = file;
    }

    /**
     * @return - true, if file is valid
     * @throws IOException - if file does not exist and can't be created
     */
    public boolean isValid() throws IOException {
        try {
            if (!file.exists()) return file.createNewFile();
        } catch (IOException e) {
            throw new IOException("The file does not exist and can't be created. " + e.getMessage(), e);
        }
        return true;
    }
}
