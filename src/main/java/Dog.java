/**
 * Created by George Fouche on 12/25/19.
 */
public class Dog extends Pet {

    private String color;

    public Dog(String name) {
        super(name);
        this.color="black";
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
