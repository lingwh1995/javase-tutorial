package headfirst.designpatterns.factory.pizzafm;

/**
 * 纽约风格奶酪披萨
 *
 * @author lingwh
 * @date 2023/12/7 17:16
 */
public class NYStyleCheesePizza extends Pizza {

    public NYStyleCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated Reggiano Cheese");
    }
}
