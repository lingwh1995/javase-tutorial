package action.responsibility.responsibility_a;

/**
 * 客户端测试
 *
 * @author lingwh
 * @date 2019/8/27 18:00
 */
public class Client {

    public static void main(String[] args) {
        // 先要组装职责链
        Handler h1 = new ConcreteHandler1();
        Handler h2 = new ConcreteHandler2();

        h1.setSuccessor(h2);
        // 然后提交请求
        h1.handleRequest();
    }
}
