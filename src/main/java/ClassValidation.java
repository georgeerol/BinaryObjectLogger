import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

/**
 * Created by George Fouche on 12/25/19.
 */
public class ClassValidation {

    String tClassName;

    public ClassValidation(String tClassName) {
        this.tClassName = tClassName;
    }


    /**
     * @return
     */
    public boolean isValid() {
        try {
            Class.forName(tClassName);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }

    }

    public boolean isInstanceOfBinaryLoggable() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> className = Class.forName(tClassName);
        Object constructClass = className.getConstructor().newInstance();
        if (!(constructClass instanceof BinaryLoggable)) {
            throw new IllegalStateException(tClassName + " does not implement BinaryLoggable");
        }

        return true;
    }

}
