package headfirst.designpatterns.combining.factory;

/**
 * 鸭子工厂
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
