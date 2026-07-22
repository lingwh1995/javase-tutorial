package org.bluebridge.structure.bridge.bridge_c;

/**
 * 邮件特急消息
 *
 * @author lingwh
 * @date 2019/7/24 11:35
 */
public class EmailSpecialUrgencyMessage implements SpecialUrgencyMessage {

    /**
     * 催促功能，没有发送就催一下
     */
    @Override
    public void hurry() {
        System.out.println("催一下......");
    }

    @Override
    public boolean sendMssage(String message, String receiver) {
        System.out.println("使用邮件发送消息......");
        hurry();
        return true;
    }
}
