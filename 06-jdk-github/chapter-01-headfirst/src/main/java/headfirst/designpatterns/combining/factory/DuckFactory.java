package headfirst.designpatterns.combining.factory;

/**
 * @author lingwh
 * @desc 鸭子工厂
 * @date 2026/7/9 00:00
 */
public class DuckFactory extends AbstractDuckFactory {

    public Quackable createMallardDuck() {
        return new MallardDuck();
    }

    public Quackable createRedheadDuck() {
        return new RedheadDuck();
    }

    public Quackable createDuckCall() {
        return new DuckCall();
    }

    public Quackable createRubberDuck() {
        return new RubberDuck();
    }
}
