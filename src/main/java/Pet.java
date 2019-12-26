import java.io.IOException;

/**
 * Created by George Fouche on 12/25/19.
 */
public class Pet implements BinaryLoggable {

    private String name =null;
    private boolean vegetarian;
    private String eats;
    private int noOfLegs;

    public Pet() {
    }

    public Pet(String name) {
        this.name = name;
    }





    @Override
    public byte[] toBytes() {
        return name.getBytes();
    }

    @Override
    public void fromBytes(byte[] rawBytes) {
        this.name = new String(rawBytes);

    }
}
