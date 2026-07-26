package org.bluebridge.structure.decorator.decorator_c;

/**
 * 具体构件
 *
 * @author lingwh
 * @date 2026/7/22 09:22
 */
public class ConcreteComponent implements Component {

    public ConcreteComponent() {}

    public void operation() {
        System.out.println("调用具体构件角色的方法operation()");
    }
}
