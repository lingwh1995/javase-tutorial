package create.simplefactory.simplefactory_b;

/**
 * @author lingwh
 * @desc 客户端:
 * @date 2026/7/9 00:00
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        OrderPizza greekPizza = new OrderPizza("greek");
        Pizza greek = greekPizza.order();
        OrderPizza asianPizza = new OrderPizza("asian");
        Pizza asian = asianPizza.order();
    }
}
