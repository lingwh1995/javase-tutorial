package action.visitor.visitor_f;

/**
 * 访问组合对象结构的访问者接口
 *
 * @author lingwh
 * @date 2019/10/11 13:48
 */
public interface Visitor {

    /**
     * 访问组合对象，相当于给组合对象添加访问者的功能
     *
     * @param composite 组合对象
     */
    void visitComposite(Composite composite);

    /**
     * 访问叶子对象，相当于给叶子对象添加访问者的功能
     *
     * @param leaf 叶子对象
     */
    void visitLeaf(Leaf leaf);
}
