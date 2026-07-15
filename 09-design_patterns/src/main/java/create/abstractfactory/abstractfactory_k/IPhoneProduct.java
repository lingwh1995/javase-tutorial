package create.abstractfactory.abstractfactory_k;

/**
 * PhoneProduct接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
