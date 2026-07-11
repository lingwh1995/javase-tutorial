package structure.decorator.decorator_a;

/**
 * @author lingwh
 * @desc 真实对象
 * @date 2019/3/23 00:00
 */
public class Car implements ICar {

    @Override
    public void run() {
        System.out.println("陆地上跑......");
    }
}
