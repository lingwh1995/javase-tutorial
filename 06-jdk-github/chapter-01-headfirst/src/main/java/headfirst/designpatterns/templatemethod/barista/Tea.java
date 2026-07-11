package headfirst.designpatterns.templatemethod.barista;

/**
 * @author lingwh
 * @desc 茶
 * @date 2026/7/9 00:00
 */
public class Tea extends CaffeineBeverage {
    public void brew() {
        System.out.println("Steeping the tea");
    }

    public void addCondiments() {
        System.out.println("Adding Lemon");
    }
}
