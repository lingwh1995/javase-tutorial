package headfirst.designpatterns.templatemethod.barista;

/**
 * 含咖啡因饮料
 *
 * @author lingwh
 * @date 2023/12/7 16:26
 */
public abstract class CaffeineBeverage {

    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    abstract void brew();

    abstract void addCondiments();

    void boilWater() {
        System.out.println("Boiling water");
    }

    void pourInCup() {
        System.out.println("Pouring into cup");
    }
}
