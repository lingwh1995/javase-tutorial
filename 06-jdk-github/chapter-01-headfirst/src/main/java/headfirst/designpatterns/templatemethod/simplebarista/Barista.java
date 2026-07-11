package headfirst.designpatterns.templatemethod.simplebarista;

/**
 * @author lingwh
 * @desc 咖啡师测试类
 * @date 2026/7/9 00:00
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
