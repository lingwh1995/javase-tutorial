package org.bluebridge.action.visitor.visitor_a;

/**
 * 访问者接口
 *
 * @author lingwh
 * @date 2019/10/11 9:14
 */
public interface Visitor {

    /**
     * 访问元素 A，相当于给元素 A 添加访问者的功能
     *
     * @param elementA
     */
    void visitConcreteElementA(ConcreteElementA elementA);

    /**
     * 访问元素 B，相当于给元素 B 添加访问者的功能
     *
     * @param elementB
     */
    void visitConcreteElementB(ConcreteElementB elementB);
}
