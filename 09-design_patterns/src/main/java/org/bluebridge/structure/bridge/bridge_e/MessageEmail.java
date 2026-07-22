package org.bluebridge.structure.bridge.bridge_e;

/**
 * 邮件类型的消息
 *
 * @author lingwh
 * @date 2019/7/24 13:45
 */
public class MessageEmail implements MessageImplementor {

    /**
     * @param message 要发送的消息内容
     * @param toUser  接收消息的用户
     */
    @Override
    public void send(String message, String toUser) {
        System.out.println("使用邮件短消息的方法，发送消息'" + message + "'给" + toUser);
    }
}
