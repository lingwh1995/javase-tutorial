package headfirst.designpatterns.factory.pizzas;

/**
 * 奶酪披萨
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
