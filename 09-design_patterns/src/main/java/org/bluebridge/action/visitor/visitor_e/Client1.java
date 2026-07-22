package org.bluebridge.action.visitor.visitor_e;

/**
 * 客户端1
 *
 * @author lingwh
 * @date 2019/10/11 14:02
 */
public class Client1 {

    public static void main(String[] args) {
        // 定义所有的组合对象
        Component root = new Composite("服装");
        Component c1 = new Composite("男装");
        Component c2 = new Composite("女装");

        // 定义所有的叶子对象
        Component leaf1 = new Leaf("衬衣");
        Component leaf2 = new Leaf("夹克");
        Component leaf3 = new Leaf("裙子");
        Component leaf4 = new Leaf("套装");

        // 按照树的结构来组合组合对象和叶子对象
        root.addChild(c1);
        root.addChild(c2);

        c1.addChild(leaf1);
        c1.addChild(leaf2);
        c2.addChild(leaf3);
        c2.addChild(leaf4);
        // 定义组件数据，组装对象树，跟刚才的测试一样，这里就省略了
        Visitor psVisitor = new PrintNameVisitor();
        root.accept(psVisitor);
    }
}
