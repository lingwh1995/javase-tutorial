package headfirst.designpatterns.adapter.ducks;

/**
 * 火鸡适配器
 *
 * @author lingwh
 * @date 2023/12/7 15:26
 */
public class TurkeyAdapter implements Duck {

    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
