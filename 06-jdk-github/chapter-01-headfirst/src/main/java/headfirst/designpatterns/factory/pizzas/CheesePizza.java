package headfirst.designpatterns.factory.pizzas;

/**
 * @author lingwh
 * @desc 奶酪披萨
 * @date 2026/7/9 00:00
 */
public class CheesePizza extends Pizza {

    public CheesePizza() {
        name = "Cheese Pizza";
        dough = "Regular Crust";
        sauce = "Marinara Pizza Sauce";
        toppings.add("Fresh Mozzarella");
        toppings.add("Parmesan");
    }
}
