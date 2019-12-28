package loggable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents an entity that can be logged by {@code BinaryLogger}
 * Created by George Fouche on 12/26/19.
 */
public class Loggable implements BinaryLoggable {
    private String logMessage;

    public Loggable() {

    }

    /**
     * Create a log message with the current date and the log message
     *
     * @param message- the log message
     */
    public Loggable(String message) {
        StringBuilder sb = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        sb.append(dateFormat.format(date)).append("-");
        sb.append("[").append(message).append("]");
        logMessage = sb.toString();
    }

    /**
     * Set a custom log message with no date
     *
     * @param logMessage
     */
    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    /**
     * @return the log message
     */
    public String getLogMessage() {
        return logMessage;
    }

    /**
     * @return - serialize the fields of the log message into a byte array.
     */
    @Override
    public byte[] toBytes() {
        return logMessage.getBytes();
    }

    /**
     * @param rawBytes Deserialize the log message from given byte array.
     */
    @Override
    public void fromBytes(byte[] rawBytes) {
        this.logMessage = new String(rawBytes);

    }
}
