package create.factorymethod.factorymethod_a;

/**
 * 比亚迪工厂
 *
 * @author lingwh
 * @date 2019/3/10 19:02
 */
public class BydFactory extends CarFactory {

    @Override
    protected Car createCar() {
        return new Byd();
    }
}
