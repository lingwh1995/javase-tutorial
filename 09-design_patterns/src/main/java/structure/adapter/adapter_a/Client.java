package structure.adapter.adapter_a;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Client {

    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.chaging(new VoltageAdapter());
    }
}
