package action.visitor.visitor_a;

/**
 * @author lingwh
 * @desc 具体元素A
 * @date 2019/10/11 9:21
 */
public class ConcreteElementA extends Element {

    /**
     * 接受访问者的访问
     *
     * @param visitor 访问者对象
     */
    @Override
    void accept(Visitor visitor) {
        visitor.visitConcreteElementA(this);
    }

    /**
     * 示例方法,表示元素已有的功能实现
     */
    public void operationA() {
        // 已有的功能实现
        System.out.println("Im operationA......");
    }
}
