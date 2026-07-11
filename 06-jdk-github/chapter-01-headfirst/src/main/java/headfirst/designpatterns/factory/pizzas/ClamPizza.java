package headfirst.designpatterns.factory.pizzas;

/**
 * @author lingwh
 * @desc 蛤蜊披萨
 * @date 2026/7/9 00:00
 */
public class ClamPizza extends Pizza {
    public ClamPizza() {
        name = "Clam Pizza";
        dough = "Thin crust";
        sauce = "White garlic sauce";
        toppings.add("Clams");
        toppings.add("Grated parmesan cheese");
    }
}
