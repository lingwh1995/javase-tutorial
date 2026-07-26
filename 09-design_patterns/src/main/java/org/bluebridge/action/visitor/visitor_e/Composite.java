package org.bluebridge.action.visitor.visitor_e;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合对象，可以包含其它组合对象或者叶子对象， 相当于访问者模式的具体 Element 实现对象
 *
 * @author lingwh
 * @date 2019/10/11 13:50
 */
public class Composite extends Component {

    @Override
    public void accept(Visitor visitor) {
        // 回调访问者对象的相应方法
        visitor.visitComposite(this);
        // 循环子元素，让子元素也接受访问
        for (Component c : childComponents) {
            // 调用子对象接受访问，变相实现递归
            c.accept(visitor);
        }
    }

    /**
     * 用来存储组合对象中包含的子组件对象
     */
    private List<Component> childComponents = new ArrayList<Component>();

    /**
     * 组合对象的名字
     */
    private String name = "";

    /**
     * 构造方法，传入组合对象的名字
     *
     * @param name 组合对象的名字
     */
    public Composite(String name) {
        this.name = name;
    }

    @Override
    public void addChild(Component child) {
        childComponents.add(child);
    }

    public String getName() {
        return name;
    }
}
