package org.bluebridge.action.visitor.visitor_a;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2019/10/11 9:28
 */
public class Client {

    public static void main(String[] args) {
        // 创建 ObjectStructure
        ObjectStructure os = new ObjectStructure();
        // 创建要加入对象结构的元素
        Element eleA = new ConcreteElementA();
        Element eleB = new ConcreteElementB();
        // 把元素加入对象结构
        os.addElement(eleA);
        os.addElement(eleB);

        // 创建访问者
        Visitor visitor = new ConcreteVisitor1();
        // 调用业务处理的方法
        os.handleRequest(visitor);
    }
}
