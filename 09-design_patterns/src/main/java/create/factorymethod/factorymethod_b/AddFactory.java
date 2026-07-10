package create.factorymethod.factorymethod_b;

/**
 * @author lingwh
 * @desc 加法工厂
 * @date 2026/7/9 00:00
 */
public class AddFactory extends OperationFactory {

    @Override
    protected Operation createOperation() {
        System.out.println("加法操作工厂...");
        return new Add();
    }
}
