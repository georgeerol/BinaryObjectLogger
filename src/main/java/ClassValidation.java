/**
 * Created by George Fouche on 12/25/19.
 */
public class ClassValidation {

    String tClassName;

    public ClassValidation(String tClassName) {
        this.tClassName = tClassName;
    }


    public boolean isValid() {
        try {
            Class.forName(tClassName);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }

    }

}
