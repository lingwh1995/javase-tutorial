package headfirst.designpatterns.templatemethod.barista;

/**
 * 咖啡
 *
 * @author lingwh
 * @date 2023/12/7 15:12
 */
public class Coffee extends CaffeineBeverage {

    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}
