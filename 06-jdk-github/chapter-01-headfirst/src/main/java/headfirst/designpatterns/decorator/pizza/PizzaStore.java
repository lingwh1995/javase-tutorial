package headfirst.designpatterns.decorator.pizza;

/**
 * 披萨店
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class PizzaStore {

    public static void main(String args[]) {
        Pizza pizza = new ThincrustPizza();
        Pizza cheesePizza = new Cheese(pizza);
        Pizza greekPizza = new Olives(cheesePizza);

        System.out.println(greekPizza.getDescription() + " $" + greekPizza.cost());
    }
}
