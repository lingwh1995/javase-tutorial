package org.bluebridge.structure.proxy.virtualproxy.vritualproxy_a;

/**
 * 虚拟代理客户端
 *
 * @author lingwh
 * @date 2019/9/24 18:13
 */
public class Client {

    public static void main(String[] args) {
        // 有很多人来找老板，老板在忙，助手先把所有事情安置好
        Assistant assistant = new Assistant();
        assistant.addOrder("我找Boss面试");
        assistant.addOrder("我找Boss借钱");
        assistant.addOrder("我找Boss聊天");

        // 收集好了，助手的职责就完成了，把 Boss 叫出来，让 Boss 处理. 或者说 approve 这件事，助手是做不了的，只能叫出 Boss 来做.
        assistant.approve();

        // Boss 刚才就被邀请过来，现在就在现场. 所以就不需要助手转告给 Boss 了. 大家告诉助手的事情，Boss 也会听到
        assistant.addOrder("我找Boss吃饭");
        assistant.addOrder("我找Boss喝酒");
        assistant.approve();
    }
}
