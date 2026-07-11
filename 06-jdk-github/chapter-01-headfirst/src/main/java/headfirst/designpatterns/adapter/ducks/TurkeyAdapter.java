package headfirst.designpatterns.adapter.ducks;

/**
 * @author lingwh
 * @desc 火鸡适配器
 * @date 2026/7/9 00:00
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
