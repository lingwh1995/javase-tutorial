package headfirst.designpatterns.combining.decorator;

/**
 * @author lingwh
 * @desc 鹅适配器
 * @date 2026/7/9 00:00
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
