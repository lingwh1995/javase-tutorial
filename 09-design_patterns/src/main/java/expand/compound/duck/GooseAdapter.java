package expand.compound.duck;

/**
 * 鹅适配器
 *
 * @author lingwh
 * @date 2019/10/10 9:57
 */
public class GooseAdapter implements Quackable {

    private Goose goose;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
    }

    @Override
    public void quack() {
        goose.honk();
    }
}
