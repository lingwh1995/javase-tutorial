package headfirst.designpatterns.factory.pizzas;

/**
 * 蛤蜊披萨
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
