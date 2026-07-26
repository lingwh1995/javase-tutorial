package org.bluebridge.create.abstractfactory.abstractfactory_k;

/**
 * PhoneProduct 接口
 *
 * @author lingwh
 * @date 2026/7/22 09:25
 */
public interface IPhoneProduct {

    // 开机
    void start();

    // 关机
    void shutdown();

    // 打电话
    void callup();

    // 发邮件
    void sendSMS();
}
