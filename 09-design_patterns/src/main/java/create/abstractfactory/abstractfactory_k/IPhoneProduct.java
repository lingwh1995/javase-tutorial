package create.abstractfactory.abstractfactory_k;

/**
 * PhoneProduct接口
 *
 * @author lingwh
 * @date
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
