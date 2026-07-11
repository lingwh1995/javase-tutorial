package headfirst.designpatterns.templatemethod.barista;

/**
 * @author lingwh
 * @desc 含咖啡因饮料
 * @date 2026/7/9 00:00
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
