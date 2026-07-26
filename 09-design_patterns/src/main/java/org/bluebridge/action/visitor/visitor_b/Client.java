package org.bluebridge.action.visitor.visitor_b;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class Client {

    public static void main(String[] args) {
        // 创建 ObjectStructure
        ObjectStructure objectStructure = new ObjectStructure();

        objectStructure.attach(new Man());
        objectStructure.attach(new Woman());

        Success success = new Success();
        objectStructure.disPlay(success);
        System.out.println("-----------------");
        Fail fail = new Fail();
        // 显示测评情况
        objectStructure.disPlay(fail);
    }
}
