package headfirst.designpatterns.factory.pizzas;

/**
 * 意大利辣香肠披萨
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
