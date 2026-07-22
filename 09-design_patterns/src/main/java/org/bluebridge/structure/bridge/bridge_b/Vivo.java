package org.bluebridge.structure.bridge.bridge_b;

/**
 * Vivo手机
 *
 * @author lingwh
 * @date 2026/7/9 15:45
 */
public class Vivo implements Brand {

    @Override
    public void open() {
        System.out.println("Vivo手机开机");
    }

    @Override
    public void close() {
        System.out.println("Vivo手机关机");
    }

    @Override
    public void call() {
        System.out.println("Vivo手机打电话");
    }
}
