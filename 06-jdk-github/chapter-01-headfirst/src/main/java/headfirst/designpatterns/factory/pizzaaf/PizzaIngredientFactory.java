package headfirst.designpatterns.factory.pizzaaf;

/**
 * 披萨原料工厂接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface PizzaIngredientFactory {

    Dough createDough();

    Sauce createSauce();

    Cheese createCheese();

    Veggies[] createVeggies();

    Pepperoni createPepperoni();

    Clams createClam();
}
