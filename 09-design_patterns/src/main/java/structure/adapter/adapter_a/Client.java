package structure.adapter.adapter_a;

/**
 * @author lingwh
 * @desc 客户端
 * @date 2026/7/9 00:00
 */
public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.chaging(new VoltageAdapter());
    }
}
