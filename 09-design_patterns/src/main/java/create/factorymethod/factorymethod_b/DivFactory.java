package create.factorymethod.factorymethod_b;

/**
 * 除法工厂
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DivFactory extends OperationFactory {

    @Override
    protected Operation createOperation() {
        System.out.println("除法操作工厂...");
        return new Div();
    }
}
