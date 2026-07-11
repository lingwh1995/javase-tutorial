package create.factorymethod.factorymethod_a;

/**
 * @author lingwh
 * @desc 比亚迪工厂
 * @date 2019/3/11 00:00
 */
public class BydFactory extends CarFactory {

    @Override
    protected Car createCar() {
        return new Byd();
    }
}
