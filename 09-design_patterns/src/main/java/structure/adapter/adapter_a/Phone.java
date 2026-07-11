package structure.adapter.adapter_a;

/**
 * @author lingwh
 * @desc 手机
 * @date 2026/7/9 00:00
 */
public class Phone {
    public void chaging(IVoltage5V iVoltage5V) {
        if (iVoltage5V.output5V() == 5) {
            System.out.println("电压是5V,可以充电......");
        } else if (iVoltage5V.output5V() > 5) {
            System.out.println("电压是220V,不能充电......");
        }
    }
}
