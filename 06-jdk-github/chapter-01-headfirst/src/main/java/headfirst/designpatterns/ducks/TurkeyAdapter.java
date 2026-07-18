package headfirst.designpatterns.ducks;

/**
 * 火鸡适配器
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TurkeyAdapter implements Duck {

    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    public void quack() {
        turkey.gobble();
    }

    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
