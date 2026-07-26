package headfirst.designpatterns.ducks;

import java.util.Random;

/**
 * 鸭子适配器
 *
 * @author lingwh
 * @date 2023/12/7 12:04
 */
public class DuckAdapter implements Turkey {

    Duck duck;
    Random rand;

    public DuckAdapter(Duck duck) {
        this.duck = duck;
        rand = new Random();
    }

    public void gobble() {
        duck.quack();
    }

    public void fly() {
        if (rand.nextInt(5) == 0) {
            duck.fly();
        }
    }
}
