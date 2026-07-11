package create.factorymethod.factorymethod_a;

/**
 * @author lingwh
 * @desc 工厂的接口
 * @date 2019/3/11 00:00
 */
public abstract class CarFactory {

    public void run() {
        Car car = createCar();
        car.run();
    }

    /**
     * 工厂方法
     *
     * @return
     */
    protected abstract Car createCar();
}
