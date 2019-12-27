package loggable;

import java.io.IOException;

/**
 * Created by George Fouche on 12/26/19.
 */
public class NoEmptyConstructorLoggable implements  BinaryLoggable {

    String logMessage;

    public NoEmptyConstructorLoggable(String logMessage) {
        this.logMessage = logMessage;
    }

    @Override
    public byte[] toBytes() {
        return logMessage.getBytes();
    }

    @Override
    public void fromBytes(byte[] rawBytes) {
        this.logMessage = new String(rawBytes);

    }
}
