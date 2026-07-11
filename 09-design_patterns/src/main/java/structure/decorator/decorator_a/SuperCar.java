package structure.decorator.decorator_a;

/**
 * @author lingwh
 * @desc 装饰器
 * @date 2019/3/23 00:00
 */
public class SuperCar implements ICar {

    private ICar car;

    public SuperCar(ICar car) {
        this.car = car;
    }

    @Override
    public void run() {
        car.run();
    }
}
