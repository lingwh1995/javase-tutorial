package create.factorymethod.factorymethod_b;

/**
 * @author lingwh
 * @desc 除法工厂
 * @date 2026/7/9 00:00
 */
public class DivFactory extends OperationFactory {
    @Override
    protected Operation createOperation() {
        System.out.println("除法操作工厂...");
        return new Div();
    }
}
