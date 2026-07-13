package headfirst.designpatterns.templatemethod.barista;

/**
 * 咖啡
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Coffee extends CaffeineBeverage {

    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}
