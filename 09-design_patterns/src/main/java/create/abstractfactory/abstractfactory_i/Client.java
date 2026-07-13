package create.abstractfactory.abstractfactory_i;

/**
 * 抽象工厂装机客户端
 *
 * @author lingwh
 * @date 2019/8/7 15:30
 */
public class Client {

    public static void main(String[] args) {
        // 创建装机工程师对象
        ComputerEngineer engineer = new ComputerEngineer();
        // 客户选择并创建需要使用的装机方案对象
        // 装配华硕电脑:华硕电脑搭载Intel CPU和技嘉主板
        AbstractFactory asus = new DellFactory();
        // 告诉装机工程师自己选择的装机方案，让装机工程师组装电脑
        engineer.makeComputer(asus);

        System.out.println("--------------------------------------------");
        // 装配联想电脑
        AbstractFactory lenovo = new LenovoFactory();
        // 告诉装机工程师自己选择的装机方案，让装机工程师组装电脑
        engineer.makeComputer(lenovo);
    }
}
