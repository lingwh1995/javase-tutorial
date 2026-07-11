package structure.adapter.adapter_j;

/**
 * @author lingwh
 * @desc 绿头鸭
 * @date 2019/9/11 8:54
 */
public class MallardDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("Quack.....");
    }

    @Override
    public void fly() {
        System.out.println("I am flying......");
    }
}
