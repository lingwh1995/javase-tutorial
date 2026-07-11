package expand.compound.duck;

/**
 * @author lingwh
 * @desc 鸣叫计数器装饰者
 * @date 2019/10/10 10:22
 */
public class QuackCounter implements Quackable {
    private Quackable quackable;
    private static int numberOfQuacks = 0;

    public QuackCounter(Quackable quackable) {
        this.quackable = quackable;
    }

    @Override
    public void quack() {
        quackable.quack();
        numberOfQuacks++;
    }

    /**
     * 获取鸭子总共鸣叫次数
     *
     * @return
     */
    public static int getQuackCount() {
        return numberOfQuacks;
    }
}
