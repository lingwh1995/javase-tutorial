package structure.decorator.decorator_c;

/**
 * @author lingwh
 * @desc 具体构件
 * @date 2026/7/9 00:00
 */
public class ConcreteComponent implements Component {

    public ConcreteComponent() {}

    public void operation() {
        System.out.println("调用具体构件角色的方法operation()");
    }
}
