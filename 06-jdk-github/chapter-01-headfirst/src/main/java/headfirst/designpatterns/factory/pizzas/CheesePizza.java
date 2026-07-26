package headfirst.designpatterns.factory.pizzas;

/**
 * 奶酪披萨
 *
 * @author lingwh
 * @date 2023/12/7 16:34
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
