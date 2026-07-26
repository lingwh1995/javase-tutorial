package headfirst.designpatterns.combining.decorator;

/**
 * 鹅适配器
 *
 * @author lingwh
 * @date 2023/12/7 21:38
 */
public class GooseAdapter implements Quackable {

    Goose goose;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
    }

    @Override
    public void quack() {
        goose.honk();
    }

    @Override
    public String toString() {
        return "Goose pretending to be a Duck";
    }
}
