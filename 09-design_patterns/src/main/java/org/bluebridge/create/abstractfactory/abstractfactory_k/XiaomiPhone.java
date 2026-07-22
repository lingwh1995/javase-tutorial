package org.bluebridge.create.abstractfactory.abstractfactory_k;

/**
 * 小米手机实现类
 *
 * @author lingwh
 * @date 2019/10/18 13:32
 */
public class XiaomiPhone implements IPhoneProduct {

    @Override
    public void start() {
        System.out.println("开启小米手机");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭小米手机");
    }

    @Override
    public void callup() {
        System.out.println("小米手机打电话");
    }

    @Override
    public void sendSMS() {
        System.out.println("小米手机发邮件");
    }
}
