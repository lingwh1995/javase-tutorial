package org.bluebridge.action.visitor.visitor_a;

/**
 * 具体访问者 1
 *
 * @author lingwh
 * @date 2019/10/11 9:25
 */
public class ConcreteVisitor1 implements Visitor {

    /**
     * 访问元素 A，相当于给元素 A 添加访问者的功能
     *
     * @param elementA
     */
    @Override
    public void visitConcreteElementA(ConcreteElementA elementA) {
        // 把去访问 ConcreteElementA 时，需要执行的功能实现在这里
        // 可能需要访问元素已有的功能，比如：
        elementA.operationA();
    }

    /**
     * 访问元素 B，相当于给元素 B 添加访问者的功能
     *
     * @param elementB
     */
    @Override
    public void visitConcreteElementB(ConcreteElementB elementB) {
        // 把去访问 ConcreteElementB 时，需要执行的功能实现在这里
        // 可能需要访问元素已有的功能，比如：
        elementB.operationB();
    }
}
