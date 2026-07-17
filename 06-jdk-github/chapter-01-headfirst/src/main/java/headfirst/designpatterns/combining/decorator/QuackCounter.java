package headfirst.designpatterns.combining.decorator;

/**
 * 叫声计数器装饰者
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class QuackCounter implements Quackable {

    Quackable duck;
    static int numberOfQuacks;

    public QuackCounter(Quackable duck) {
        this.duck = duck;
    }

    public void quack() {
        duck.quack();
        numberOfQuacks++;
    }

    public static int getQuacks() {
        return numberOfQuacks;
    }

    public String toString() {
        return duck.toString();
    }
}
