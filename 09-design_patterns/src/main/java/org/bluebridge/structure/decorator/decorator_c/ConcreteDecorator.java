package org.bluebridge.structure.decorator.decorator_c;

/**
 * 具体装饰器
 *
 * @author lingwh
 * @date 2026/7/22 09:51
 */
public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        this.component = component;
    }

    public void operation() {
        component.operation();
        addedFunction();
    }

    public void addedFunction() {
        System.out.println("为具体构件角色增加额外的功能addedFunction()");
    }
}
