package structure.decorator.decorator_a;

/**
 * @author lingwh
 * @desc 具体装饰器/具体装饰角色
 * @date 2019/3/23 00:00
 */
public class FlyCar extends SuperCar {

    public FlyCar(ICar car) {
        super(car);
    }

    @Override
    public void run() {
        super.run();
        fly();
    }

    /**
     * 增强的功能
     *
     * @param
     * @return void
     * @throws
     */
    private void fly() {
        System.out.println("天上飞......");
    }
}
