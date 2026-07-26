package headfirst.designpatterns.factory.pizzafm;

/**
 * 芝加哥风格蛤蜊披萨
 *
 * @author lingwh
 * @date 2023/12/7 20:21
 */
public class ChicagoStyleClamPizza extends Pizza {

    public ChicagoStyleClamPizza() {
        name = "Chicago Style Clam Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";

        toppings.add("Shredded Mozzarella Cheese");
        toppings.add("Frozen Clams from Chesapeake Bay");
    }

    void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
