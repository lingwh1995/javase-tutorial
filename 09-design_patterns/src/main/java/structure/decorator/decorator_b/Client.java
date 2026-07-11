package structure.decorator.decorator_b;

/**
 * @author lingwh
 * @desc 咖啡店
 * @date 2026/7/9 00:00
 */
public class Client {
    public static void main(String[] args) {
        // 点一份法国咖啡
        Drink franceCoffee = new FranceCoffee();
        System.out.println(franceCoffee.cost());

        // 加牛奶
        franceCoffee = new Milk(franceCoffee);
        System.out.println(franceCoffee.getDesc());

        // 再加巧克力
        franceCoffee = new Cholate(franceCoffee);
        System.out.println(franceCoffee.getDesc());

        // 再加一份巧克力
        franceCoffee = new Cholate(franceCoffee);
        System.out.println(franceCoffee.getDesc());
    }
}
