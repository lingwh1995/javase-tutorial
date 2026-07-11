package headfirst.designpatterns.templatemethod.barista;

/**
 * @author lingwh
 * @desc 咖啡
 * @date 2026/7/9 00:00
 */
public class Coffee extends CaffeineBeverage {
    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}
