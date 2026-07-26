package org.bluebridge.structure.bridge.bridge_g;

/**
 * 使用 Email 的方法发送消息
 *
 * @author lingwh
 * @date 2019/8/6 9:14
 */
public class MessageEmail {

    /**
     * 发送消息
     *
     * @param message
     * @param toUer
     */
    public void send(String message, String toUer) {
        System.out.println("使用邮件的方法发送消息" + message + "给" + toUer);
    }
}
