package create.factorymethod.factorymethod_b;

/**
 * @author lingwh
 * @desc 运算工厂
 * @date 2026/7/9 00:00
 */
public abstract class OperationFactory {
    public Double opertion(Double a, Double b) {
        Operation operation = createOperation();
        return operation.opertion(a, b);
    }

    protected abstract Operation createOperation();
}
