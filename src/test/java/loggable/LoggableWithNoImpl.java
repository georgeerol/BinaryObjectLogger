package loggable;

import java.io.IOException;

/**
 * Created by George Fouche on 12/27/19.
 */
public class LoggableWithNoImpl implements BinaryLoggable {

    public LoggableWithNoImpl() {
    }


    @Override
    public byte[] toBytes() throws IOException {
        return new byte[0];
    }

    @Override
    public void fromBytes(byte[] rawBytes) throws IOException {
        String logMessage = new String(rawBytes);
    }
}
