package headfirst.designpatterns.decorator.pizza;

/**
 * @author lingwh
 * @desc 披萨店
 * @date 2026/7/9 00:00
 */
public class PizzaStore {

    public static void main(String args[]) {
        Pizza pizza = new ThincrustPizza();
        Pizza cheesePizza = new Cheese(pizza);
        Pizza greekPizza = new Olives(cheesePizza);

        System.out.println(greekPizza.getDescription() + " $" + greekPizza.cost());
    }
}
