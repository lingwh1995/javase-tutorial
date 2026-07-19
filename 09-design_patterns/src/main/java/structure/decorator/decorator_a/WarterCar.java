package structure.decorator.decorator_a;

/**
 * 具体装饰器/具体装饰角色
 *
 * @author lingwh
 * @date 2019/3/23 00:00
 */
public class WarterCar extends SuperCar {

    public WarterCar(ICar car) {
        super(car);
    }

    @Override
    public void run() {
        super.run();
        swim();
    }

    /**
     * 增强的功能
     */
    private void swim() {
        System.out.println("水里游......");
    }
}
