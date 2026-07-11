package create.abstractfactory.abstractfactory_k;

/**
 * @author lingwh
 * @desc 华为手机实现类
 * @date 2026/7/9 00:00
 */
public class HuaweiPhone implements IPhoneProduct {

    @Override
    public void start() {
        System.out.println("开启华为手机");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭华为手机");
    }

    @Override
    public void callup() {
        System.out.println("华为手机打电话");
    }

    @Override
    public void sendSMS() {
        System.out.println("华为手机发邮件");
    }
}
