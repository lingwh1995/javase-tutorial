package org.bluebridge.structure.bridge.bridge_e;

/**
 * 短信类消息实现类
 *
 * @author lingwh
 * @date 2019/7/24 13:43
 */
public class MessageSMS implements MessageImplementor {

    /**
     * @param message 要发送的消息内容
     * @param toUser  接收消息的用户
     */
    @Override
    public void send(String message, String toUser) {
        System.out.println("使用系统内短消息的方法，发送消息'" + message + "'给" + toUser);
    }
}
