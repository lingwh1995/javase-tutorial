package org.bluebridge.structure.adapter.adapter_b;

/**
 * 对象适配器
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class VoltageAdapter implements IVoltage5V {

    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        System.out.println("对象适配器......");
        int dst = 0;
        if (null != voltage220V) {
            int src = voltage220V.output220();
            System.out.println("获取到" + src + "V电压");
            dst = src / 44;
            System.out.println(src + "V电压-->" + dst);
        }
        return dst;
    }
}
