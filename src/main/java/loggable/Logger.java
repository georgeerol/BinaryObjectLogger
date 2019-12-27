package loggable;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by George Fouche on 12/26/19.
 */
public class Logger implements BinaryLoggable {

    String logMessage = null;

    public Logger() {

    }

    public  Logger(String message) {
        StringBuilder sb = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        sb.append(dateFormat.format(date)).append("-");
        sb.append("[").append(message).append("]");
        logMessage = sb.toString();
    }

    public String getLogMessage() {
        return logMessage;
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
