package loggable;

/**
 * Created by George Fouche on 12/26/19.
 */
public class SystemLog extends Loggable {
    private String system;

    public SystemLog() {

    }

    public SystemLog(String system, String message) {
        super(message);
        this.system = system;
    }

    @Override
    public byte[] toBytes() {
        return (getLogMessage() + "?" + system).getBytes();
    }

    public String getSystem() {
        return system;
    }

    @Override
    public void fromBytes(byte[] rawBytes) {
        String[] data = new String(rawBytes).split("\\?");
        setLogMessage(data[0]);
        this.system = data[1];

    }
}

