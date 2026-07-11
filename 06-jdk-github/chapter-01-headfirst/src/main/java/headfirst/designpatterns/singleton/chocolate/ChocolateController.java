package headfirst.designpatterns.singleton.chocolate;

/**
 * @author lingwh
 * @desc 巧克力锅炉控制器
 * @date 2026/7/9 00:00
 */
public class ChocolateController {
    public static void main(String args[]) {
        ChocolateBoiler boiler = ChocolateBoiler.getInstance();
        boiler.fill();
        boiler.boil();
        boiler.drain();

        // will return the existing instance
        ChocolateBoiler boiler2 = ChocolateBoiler.getInstance();
    }
}
