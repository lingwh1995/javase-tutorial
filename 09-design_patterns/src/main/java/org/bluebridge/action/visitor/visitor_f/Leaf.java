package org.bluebridge.action.visitor.visitor_f;

/**
 * 叶子对象，相当于访问者模式的具体 Element 实现对象
 *
 * @author lingwh
 * @date 2019/10/11 13:52
 */
public class Leaf extends Component {

    @Override
    public void accept(Visitor visitor) {
        // 回调访问者对象的相应方法
        visitor.visitLeaf(this);
    }

    /**
     * 叶子对象的名字
     */
    private String name = "";

    /**
     * 构造方法，传入叶子对象的名字
     *
     * @param name 叶子对象的名字
     */
    public Leaf(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
