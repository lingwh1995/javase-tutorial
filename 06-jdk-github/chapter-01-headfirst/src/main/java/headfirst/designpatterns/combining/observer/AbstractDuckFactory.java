package headfirst.designpatterns.combining.observer;

/**
 * @author lingwh
 * @desc 抽象鸭子工厂
 * @date 2026/7/9 00:00
 */
public abstract class AbstractDuckFactory {

  public abstract Quackable createMallardDuck();

  public abstract Quackable createRedheadDuck();

  public abstract Quackable createDuckCall();

  public abstract Quackable createRubberDuck();
}
