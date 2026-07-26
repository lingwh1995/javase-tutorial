package org.bluebridge.structure.adapter.adapter_a;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/7/22 16:28
 */
public class Client {

    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.chaging(new VoltageAdapter());
    }
}
