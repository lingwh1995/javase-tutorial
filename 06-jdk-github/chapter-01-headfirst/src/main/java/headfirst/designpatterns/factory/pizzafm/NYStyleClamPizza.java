package headfirst.designpatterns.factory.pizzafm;

/**
 * @author lingwh
 * @desc 纽约风格蛤蜊披萨
 * @date 2026/7/9 00:00
 */
public class NYStyleClamPizza extends Pizza {

    public NYStyleClamPizza() {
        name = "NY Style Clam Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated Reggiano Cheese");
        toppings.add("Fresh Clams from Long Island Sound");
    }
}
