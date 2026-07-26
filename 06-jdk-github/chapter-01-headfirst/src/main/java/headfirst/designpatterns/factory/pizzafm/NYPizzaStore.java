package headfirst.designpatterns.factory.pizzafm;

/**
 * 纽约披萨店
 *
 * @author lingwh
 * @date 2023/12/7 17:53
 */
public class NYPizzaStore extends PizzaStore {

    @Override
    Pizza createPizza(String item) {
        if (item.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else if (item.equals("veggie")) {
            return new NYStyleVeggiePizza();
        } else if (item.equals("clam")) {
            return new NYStyleClamPizza();
        } else if (item.equals("pepperoni")) {
            return new NYStylePepperoniPizza();
        } else {
            return null;
        }
    }
}
