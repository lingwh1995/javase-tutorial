package structure.decorator.decorator_b;

/**
 * @author lingwh
 * @desc 法国咖啡：被装饰者
 * @date 2026/7/9 00:00
 */
public class FranceCoffee extends Coffee {
    public FranceCoffee() {
        super.setDesc("法国咖啡");
        super.setPrice(20.0);
    }
}
