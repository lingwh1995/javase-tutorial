package create.factorymethod.factorymethod_a;

/**
 * 调用者
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Client {

    public static void main(String[] args) {
        CarFactory audiFactory = new AudiFactory();
        audiFactory.run();

        CarFactory bydFactory = new BydFactory();
        bydFactory.run();
    }
}
