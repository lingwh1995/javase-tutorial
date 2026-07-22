package org.bluebridge.structure.bridge.bridge_g;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2019/8/6 9:27
 */
public class Client {

    public static void main(String[] args) {
        // 创建具体的实现对象
        MessageEmail impl = new MessageEmail();
        // 创建一个普通消息对象
        AbstractMessage m = new CommonMessage(impl);
        m.sendMessage("请喝一杯茶", "小李");
        // 创建一个紧急消息对象
        m = new UrgencyMessage(impl);
        m.sendMessage("请喝一杯茶", "小李");
        // 创建一个特急消息对象
        m = new SpecialUrgencyMessage(impl);
        m.sendMessage("请喝一杯茶", "小李");
    }
}
