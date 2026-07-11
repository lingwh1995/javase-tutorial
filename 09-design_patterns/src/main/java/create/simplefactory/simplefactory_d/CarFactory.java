package create.simplefactory.simplefactory_d;

/**
 * @author lingwh
 * @desc 生产汽车的工厂
 * @date 2019/3/10 00:00
 */
public class CarFactory {

    /**
     * 使用反射增强工厂方法模式
     *
     * @param clazz
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Car createCar(Class clazz) throws IllegalAccessException, InstantiationException {
        return (Car) clazz.newInstance();
    }
}
