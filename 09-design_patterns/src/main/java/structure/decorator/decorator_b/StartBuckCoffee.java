package structure.decorator.decorator_b;

/**
 * @author lingwh
 * @desc 星巴克咖啡：被装饰者
 * @date 2026/7/9 00:00
 */
public class StartBuckCoffee extends Coffee {
    public StartBuckCoffee() {
        super.setDesc("星巴克咖啡");
        super.setPrice(25.9);
    }
}
