package headfirst.designpatterns.combining.decorator;

/**
 * 叫声计数器装饰者
 *
 * @author lingwh
 * @date 2023/12/7 08:06
 */
public class QuackCounter implements Quackable {

    Quackable duck;
    static int numberOfQuacks;

    public QuackCounter(Quackable duck) {
        this.duck = duck;
    }

    @Override
    public void quack() {
        duck.quack();
        numberOfQuacks++;
    }

    public static int getQuacks() {
        return numberOfQuacks;
    }

    @Override
    public String toString() {
        return duck.toString();
    }
}
