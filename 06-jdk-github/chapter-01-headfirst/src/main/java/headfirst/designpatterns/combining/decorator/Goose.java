package headfirst.designpatterns.combining.decorator;

/**
 * 鹅
 *
 * @author lingwh
 * @date 2023/12/7 20:12
 */
public class Goose {

    public void honk() {
        System.out.println("Honk");
    }

    @Override
    public String toString() {
        return "Goose";
    }
}
