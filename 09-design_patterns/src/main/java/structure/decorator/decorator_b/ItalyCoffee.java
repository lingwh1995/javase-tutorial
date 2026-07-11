package structure.decorator.decorator_b;

/**
 * @author lingwh
 * @desc 意大利咖啡：被装饰者
 * @date 2026/7/9 00:00
 */
public class ItalyCoffee extends Coffee {
    public ItalyCoffee() {
        super.setDesc("意大利咖啡");
        super.setPrice(109.6);
    }
}
