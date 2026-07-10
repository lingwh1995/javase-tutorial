package create.abstractfactory.abstractfactory_i;

/**
 * @author lingwh
 * @desc 抽象工厂的接口，声明创建抽象产品的操作
 * @date 2019/8/7 14:47
 */
public interface AbstractFactory {

  /**
   * 创建硬件
   *
   * @return
   */
  Object createHardware(int type);
}
