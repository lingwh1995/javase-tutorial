package headfirst.designpatterns.factory.pizzafm;

/**
 * 纽约风格蔬菜披萨
 *
 * @author lingwh
 * @date 2023/12/7 15:25
 */
public class NYStyleVeggiePizza extends Pizza {

    public NYStyleVeggiePizza() {
        name = "NY Style Veggie Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated Reggiano Cheese");
        toppings.add("Garlic");
        toppings.add("Onion");
        toppings.add("Mushrooms");
        toppings.add("Red Pepper");
    }
}
