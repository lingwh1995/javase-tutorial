package headfirst.designpatterns.combining.observer;

/**
 * 鹅
 *
 * @author lingwh
 * @date 2023/12/7 10:48
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
