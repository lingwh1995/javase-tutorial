package headfirst.designpatterns.templatemethod.barista;

/**
 * 茶
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Tea extends CaffeineBeverage {

    public void brew() {
        System.out.println("Steeping the tea");
    }

    public void addCondiments() {
        System.out.println("Adding Lemon");
    }
}
