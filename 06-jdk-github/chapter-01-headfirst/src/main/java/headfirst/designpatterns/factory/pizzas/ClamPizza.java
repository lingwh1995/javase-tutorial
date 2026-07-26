package headfirst.designpatterns.factory.pizzas;

/**
 * 蛤蜊披萨
 *
 * @author lingwh
 * @date 2023/12/7 15:07
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
