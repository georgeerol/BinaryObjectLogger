package loggable;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by George Fouche on 12/26/19.
 */
public class NotLoggable  {

    private String logMessage = null;

    public NotLoggable() {

    }

    public NotLoggable(String message) {
        logMessage = message;
    }

    public String getLogMessage() {
        return logMessage;
    }

}
