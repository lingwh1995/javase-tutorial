package create.factorymethod.factorymethod_b;

/**
 * @author lingwh
 * @desc 减法工厂
 * @date 2026/7/9 00:00
 */
public class SubFactory extends OperationFactory {
    @Override
    protected Operation createOperation() {
        System.out.println("减法操作工厂...");
        return new Sub();
    }
}
