package loggable;

/**
 * This class extends Loggable  and it represents an entity that can be logged by {@code BinaryLogger}
 * Created by George Fouche on 12/26/19.
 */
public class SystemLog extends Loggable {
    private String system;

    public SystemLog() {

    }

    /**
     * @param system  - a system can be a computer,mobile,tv etc.
     * @param message - the log message
     */
    public SystemLog(String system, String message) {
        super(message);
        this.system = system;
    }

    /**
     * Serialize system and log message into a byte array
     *
     * @return- system and log message into a byte array
     */
    @Override
    public byte[] toBytes() {
        return (getLogMessage() + "?" + system).getBytes();
    }

    /**
     * @return system name
     */
    public String getSystem() {
        return system;
    }

    /**
     * Deserialize the log message and system from given byte array
     *
     * @param rawBytes- log message and system raw bytes
     */
    @Override
    public void fromBytes(byte[] rawBytes) {
        String[] data = new String(rawBytes).split("\\?");
        setLogMessage(data[0]);
        this.system = data[1];

    }
}

