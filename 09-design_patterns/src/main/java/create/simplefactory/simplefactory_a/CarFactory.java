package create.simplefactory.simplefactory_a;

/**
 * @author lingwh
 * @desc 生产汽车的工厂
 * @date 2019/3/10 00:00
 */
public class CarFactory {

    /**
     * 简单工厂中创建车的方法
     *
     * @param carName
     * @return Car 返回类型
     * @throws
     */
    public static Car createCar(String carName) {
        if ("audi".equals(carName)) {
            return new Audi();
        } else if ("byd".equals(carName)) {
            return new Byd();
        } else {
            return null;
        }
    }
}
