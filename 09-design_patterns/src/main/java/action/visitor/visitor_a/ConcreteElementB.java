package action.visitor.visitor_a;

/**
 * 具体元素B
 *
 * @author lingwh
 * @date 2019/10/11 9:22
 */
public class ConcreteElementB extends Element {

    /**
     * 接受访问者的访问
     *
     * @param visitor 访问者对象
     */
    @Override
    void accept(Visitor visitor) {
        visitor.visitConcreteElementB(this);
    }

    /**
     * 示例犯法,表示元素已有的功能实现
     */
    public void operationB() {
        // 已有的功能实现
        System.out.println("Im operationB......");
    }
}
