package headfirst.designpatterns.combining.composite;

/**
 * @author lingwh
 * @desc 叫声计数器
 * @date 2026/7/9 00:00
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
