package structure.adapter.adapter_b;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Client {

    public static void main(String[] args) {
        Voltage220V voltage220V = new Voltage220V();
        // 获取到适配器
        VoltageAdapter voltageAdapter = new VoltageAdapter(voltage220V);

        Phone phone = new Phone();
        phone.charging(voltageAdapter);
    }
}
