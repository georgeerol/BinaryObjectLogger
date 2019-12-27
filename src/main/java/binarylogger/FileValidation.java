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
        if (file == null) throw new IllegalArgumentException("The output file is not provided");

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new IOException("Unable to create file. " + e.getMessage(), e);
        }

        if(!file.canWrite())
                throw new IOException(("Unable to write to the giving file " + file.getPath()));

        if(!file.canRead())
            throw new IOException(("Unable to read to the giving file " + file.getPath()));

        return true;
    }
}
