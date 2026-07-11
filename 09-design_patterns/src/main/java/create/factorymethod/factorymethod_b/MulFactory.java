package create.factorymethod.factorymethod_b;

/**
 * @author lingwh
 * @desc 乘法工厂
 * @date 2026/7/9 00:00
 */
public class MulFactory extends OperationFactory {
    @Override
    protected Operation createOperation() {
        System.out.println("乘法工厂类...");
        return new Mul();
    }
}
