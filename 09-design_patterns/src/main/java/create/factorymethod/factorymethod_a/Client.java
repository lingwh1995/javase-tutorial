package create.factorymethod.factorymethod_a;

/**
 * @author lingwh
 * @desc 调用者
 * @date 2019/3/11 00:00
 */
public class Client {
    public static void main(String[] args) {
        CarFactory audiFactory = new AudiFactory();
        audiFactory.run();

        CarFactory bydFactory = new BydFactory();
        bydFactory.run();
    }
}
