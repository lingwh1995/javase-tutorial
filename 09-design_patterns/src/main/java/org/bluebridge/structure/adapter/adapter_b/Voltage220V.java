package org.bluebridge.structure.adapter.adapter_b;

/**
 * 220V 电压
 *
 * @author lingwh
 * @date 2026/7/22 12:33
 */
public class Voltage220V {

    public int output220() {
        int src = 220;
        System.out.println("输出" + src + "V电压......");
        return src;
    }
}
