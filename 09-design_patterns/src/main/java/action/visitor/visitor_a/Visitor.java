package action.visitor.visitor_a;

/**
 * @author lingwh
 * @desc 访问者接口
 * @date 2019/10/11 9:14
 */
public interface Visitor {
    /**
     * 访问元素A，相当于给元素A添加访问者的功能
     *
     * @param elementA
     */
    void visitConcreteElementA(ConcreteElementA elementA);

    /**
     * 访问元素B，相当于给元素B添加访问者的功能
     *
     * @param elementB
     */
    void visitConcreteElementB(ConcreteElementB elementB);
}
