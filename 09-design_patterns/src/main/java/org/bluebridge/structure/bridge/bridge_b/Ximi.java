package org.bluebridge.structure.bridge.bridge_b;

/**
 * 小米手机
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class Ximi implements Brand {

    @Override
    public void open() {
        System.out.println("小米手机开机");
    }

    @Override
    public void close() {
        System.out.println("小米手机关机");
    }

    @Override
    public void call() {
        System.out.println("小米手机打电话");
    }
}
