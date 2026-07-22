package org.bluebridge.expand.compound.duck;

/**
 * 鸭子工厂
 *
 * @author lingwh
 * @date 2019/10/10 11:19
 */
public class DuckFactory extends AbstractDuckFactory {

    @Override
    Quackable createMallardDuck() {
        return new MallardDuck();
    }

    @Override
    Quackable createRedHeadDuck() {
        return new RedHeadDuck();
    }

    @Override
    Quackable createRubberDuck() {
        return new RubberDuck();
    }

    @Override
    Quackable createDuckcall() {
        return new DuckCall();
    }
}
