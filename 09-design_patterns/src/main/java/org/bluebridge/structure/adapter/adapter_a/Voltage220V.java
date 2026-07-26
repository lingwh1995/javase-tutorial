package org.bluebridge.structure.adapter.adapter_a;

/**
 * 220V 电压
 *
 * @author lingwh
 * @date 2026/7/22 11:53
 */
public class Voltage220V {

    public int output220V() {
        int voltage = 220;
        System.out.println("输出" + voltage + "v电压");
        return voltage;
    }
}
