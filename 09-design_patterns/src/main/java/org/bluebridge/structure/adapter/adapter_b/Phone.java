package org.bluebridge.structure.adapter.adapter_b;

/**
 * 手机
 *
 * @author lingwh
 * @date 2026/7/22 15:46
 */
public class Phone {

    public void charging(IVoltage5V iVoltage5V) {
        if (iVoltage5V.output5V() == 5) {
            System.out.println("5V，可以充电......");
        } else if (iVoltage5V.output5V() == 220) {
            System.out.println("220V，不可以充电......");
        }
    }
}
