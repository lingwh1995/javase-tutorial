package org.bluebridge.expand.compound.duck;

/**
 * 计数鸭子工厂
 *
 * @author lingwh
 * @date 2019/10/10 11:23
 */
public class CountingDuckFactory extends AbstractDuckFactory {

    @Override
    Quackable createMallardDuck() {
        return new QuackCounter(new MallardDuck());
    }

    @Override
    Quackable createRedHeadDuck() {
        return new QuackCounter(new RedHeadDuck());
    }

    @Override
    Quackable createRubberDuck() {
        return new QuackCounter(new RubberDuck());
    }

    @Override
    Quackable createDuckcall() {
        return new QuackCounter(new DuckCall());
    }
}
