package headfirst.designpatterns.combining.adapter;

/**
 * 鹅适配器
 *
 * @author lingwh
 * @date 2023/12/7 20:54
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
