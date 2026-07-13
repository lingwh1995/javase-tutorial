package headfirst.designpatterns.templatemethod.barista;

/**
 * 含咖啡因饮料
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
