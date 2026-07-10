package expand.compound.duck;

/**
 * @author lingwh
 * @desc 抽象工厂模式
 * @date 2019/10/10 11:15
 */
public abstract class AbstractDuckFactory {

    abstract Quackable createMallardDuck();

    abstract Quackable createRedHeadDuck();

    abstract Quackable createRubberDuck();

    abstract Quackable createDuckcall();
}
