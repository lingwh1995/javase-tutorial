package headfirst.designpatterns.combining.decorator;

/**
 * 鹅适配器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class GooseAdapter implements Quackable {

    Goose goose;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
    }

    public void quack() {
        goose.honk();
    }

    public String toString() {
        return "Goose pretending to be a Duck";
    }
}
