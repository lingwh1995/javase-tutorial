package structure.decorator.decorator_c;

/**
 * @author lingwh
 * @desc 具体装饰器
 * @date 2026/7/9 00:00
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
