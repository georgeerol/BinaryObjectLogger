package loggable;

import java.io.IOException;

/**
 * loggable.BinaryLoggable represents an entity that can be logged by {@code BinaryLogger}.
 * Created by George Fouche on 12/23/19.
 */
public interface BinaryLoggable {
    /**
     * Serialize the fields of this object into a byte array.
     */
    byte[] toBytes() throws IOException;
    /**
     * Deserialize the fields of this object from given byte array.
     */
    void fromBytes(byte[] rawBytes) throws IOException;


}
