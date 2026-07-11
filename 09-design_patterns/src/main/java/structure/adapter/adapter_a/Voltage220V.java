package structure.adapter.adapter_a;

/**
 * @author lingwh
 * @desc 220V电压
 * @date 2026/7/9 00:00
 */
public class Voltage220V {
    public int output220V() {
        int voltage = 220;
        System.out.println("输出" + voltage + "v电压");
        return voltage;
    }
}
