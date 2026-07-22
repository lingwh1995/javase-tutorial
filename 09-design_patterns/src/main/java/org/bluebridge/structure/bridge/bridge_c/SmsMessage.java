package org.bluebridge.structure.bridge.bridge_c;

/**
 * 发送短信类型的消息
 *
 * @author lingwh
 * @date 2019/7/24 11:04
 */
public class SmsMessage implements MessageInterface {

    @Override
    public boolean sendMssage(String message, String receiver) {
        System.out.println("使用短信发送消息......");
        return true;
    }
}
