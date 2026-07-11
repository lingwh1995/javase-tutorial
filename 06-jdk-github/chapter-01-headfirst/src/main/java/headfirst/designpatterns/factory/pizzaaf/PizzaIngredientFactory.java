package headfirst.designpatterns.factory.pizzaaf;

/**
 * @author lingwh
 * @desc 披萨原料工厂接口
 * @date 2026/7/9 00:00
 */
public interface PizzaIngredientFactory {

    Dough createDough();

    Sauce createSauce();

    Cheese createCheese();

    Veggies[] createVeggies();

    Pepperoni createPepperoni();

    Clams createClam();
}
