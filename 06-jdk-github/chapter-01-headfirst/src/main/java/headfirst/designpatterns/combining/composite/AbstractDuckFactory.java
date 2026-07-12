package headfirst.designpatterns.combining.composite;

/**
 * 抽象鸭子工厂
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public abstract class AbstractDuckFactory {

    public abstract Quackable createMallardDuck();

    public abstract Quackable createRedheadDuck();

    public abstract Quackable createDuckCall();

    public abstract Quackable createRubberDuck();
}
