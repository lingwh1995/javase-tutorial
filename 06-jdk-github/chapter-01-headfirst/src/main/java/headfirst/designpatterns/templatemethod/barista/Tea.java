package headfirst.designpatterns.templatemethod.barista;

/**
 * 茶
 *
 * @author lingwh
 * @date 2023/12/7 13:58
 */
public class Tea extends CaffeineBeverage {

    @Override
    public void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding Lemon");
    }
}
