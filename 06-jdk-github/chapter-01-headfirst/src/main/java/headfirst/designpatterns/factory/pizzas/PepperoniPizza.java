package headfirst.designpatterns.factory.pizzas;

/**
 * @author lingwh
 * @desc 意大利辣香肠披萨
 * @date 2026/7/9 00:00
 */
public class PepperoniPizza extends Pizza {
    public PepperoniPizza() {
        name = "Pepperoni Pizza";
        dough = "Crust";
        sauce = "Marinara sauce";
        toppings.add("Sliced Pepperoni");
        toppings.add("Sliced Onion");
        toppings.add("Grated parmesan cheese");
    }
}
