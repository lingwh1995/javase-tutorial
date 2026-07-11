package headfirst.designpatterns.factory.pizzafm;

/**
 * @author lingwh
 * @desc 纽约风格奶酪披萨
 * @date 2026/7/9 00:00
 */
public class NYStyleCheesePizza extends Pizza {

    public NYStyleCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated Reggiano Cheese");
    }
}
