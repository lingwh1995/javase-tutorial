package headfirst.designpatterns.factory.pizzafm;

/**
 * 纽约风格奶酪披萨
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class NYStyleCheesePizza extends Pizza {

    public NYStyleCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated Reggiano Cheese");
    }
}
