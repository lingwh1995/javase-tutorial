package org.bluebridge.expand.compound.duck;

/**
 * 鸭子模拟器客户端
 *
 * @author lingwh
 * @date 2019/10/10 9:49
 */
public class DuckSimulatorClient {

    public static void main(String[] args) {
        MallardDuck mallardDuck = new MallardDuck();
        RedHeadDuck redHeadDuck = new RedHeadDuck();
        RubberDuck rubberDuck = new RubberDuck();
        DuckCall duckCall = new DuckCall();

        // 模拟器
        simulate(mallardDuck);
        simulate(redHeadDuck);
        simulate(rubberDuck);
        simulate(duckCall);
        System.out.println("------------------------------------------------");

        // 适配器模式:使用模拟器调用Goose类的方法
        GooseAdapter gooseAdapter = new GooseAdapter(new Goose());
        simulate(gooseAdapter);
        System.out.println("------------------------------------------------");

        // 使用装饰者模式:完成统计鸭子鸣叫次数的需求
        simulate(new QuackCounter(mallardDuck));
        System.out.println("鸣叫次数:" + QuackCounter.getQuackCount());
        simulate(new QuackCounter(redHeadDuck));
        System.out.println("鸣叫次数:" + QuackCounter.getQuackCount());
        System.out.println("------------------------------------------------");

        // 抽象工厂模式:
        CountingDuckFactory countingDuckFactory = new CountingDuckFactory();
        Quackable duckcallByCountingFactory = countingDuckFactory.createDuckcall();
        Quackable mallardDuckByCountingFactory = countingDuckFactory.createMallardDuck();
        Quackable redHeadDuckByCountingFactory = countingDuckFactory.createRedHeadDuck();
        Quackable rubberDuckByCountingFactory = countingDuckFactory.createRubberDuck();
        simulate(duckcallByCountingFactory);
        simulate(mallardDuckByCountingFactory);
        simulate(redHeadDuckByCountingFactory);
        simulate(rubberDuckByCountingFactory);
        System.out.println("鸣叫次数:" + QuackCounter.getQuackCount());
        System.out.println("------------------------------------------------");

        // 组合模式
        Flock flock = new Flock();
        flock.add(mallardDuck);
        flock.add(redHeadDuck);
        flock.add(rubberDuck);
        flock.add(duckCall);
        simulate(flock);
    }

    /**
     * 模拟器:传入任意类型的鸭子,都可以实现鸣叫
     *
     * @param mallardDuck
     */
    private static void simulate(Quackable mallardDuck) {
        mallardDuck.quack();
    }
}
