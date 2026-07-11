package action.visitor.visitor_b;

/**
 * @author lingwh
 * @desc 客户端
 * @date 2026/7/9 00:00
 */
public class Client {
    public static void main(String[] args) {
        // 创建ObjectStructure
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
