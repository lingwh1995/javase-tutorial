package headfirst.designpatterns.singleton.chocolate;

/**
 * 巧克力锅炉控制器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
