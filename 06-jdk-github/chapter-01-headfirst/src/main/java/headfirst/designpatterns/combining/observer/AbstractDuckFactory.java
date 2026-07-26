package headfirst.designpatterns.combining.observer;

/**
 * 抽象鸭子工厂
 *
 * @author lingwh
 * @date 2023/12/7 17:32
 */
public abstract class AbstractDuckFactory {

    public abstract Quackable createMallardDuck();

    public abstract Quackable createRedheadDuck();

    public abstract Quackable createDuckCall();

    public abstract Quackable createRubberDuck();
}
