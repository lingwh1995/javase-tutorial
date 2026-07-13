package headfirst.designpatterns.templatemethod.simplebarista;

/**
 * 咖啡师测试类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Barista {

    public static void main(String[] args) {
        Tea tea = new Tea();
        Coffee coffee = new Coffee();
        System.out.println("Making tea...");
        tea.prepareRecipe();
        System.out.println("Making coffee...");
        coffee.prepareRecipe();
    }
}
