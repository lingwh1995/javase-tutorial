package structure.adapter.adapter_b;

/**
 * @author lingwh
 * @desc 220V电压
 * @date 2026/7/9 00:00
 */
public class Voltage220V {
    public int output220() {
        int src = 220;
        System.out.println("输出" + src + "V电压......");
        return src;
    }
}
