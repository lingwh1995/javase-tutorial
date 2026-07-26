package headfirst.designpatterns.templatemethod.simplebarista;

/**
 * 咖啡师测试类
 *
 * @author lingwh
 * @date 2023/12/7 12:07
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
