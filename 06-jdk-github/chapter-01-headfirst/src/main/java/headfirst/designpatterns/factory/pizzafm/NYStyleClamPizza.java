package headfirst.designpatterns.factory.pizzafm;

/**
 * 纽约风格蛤蜊披萨
 *
 * @author lingwh
 * @date 2023/12/7 16:39
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
