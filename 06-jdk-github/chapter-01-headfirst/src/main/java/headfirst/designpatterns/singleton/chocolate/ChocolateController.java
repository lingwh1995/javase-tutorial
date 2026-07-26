package headfirst.designpatterns.singleton.chocolate;

/**
 * 巧克力锅炉控制器
 *
 * @author lingwh
 * @date 2023/12/7 11:37
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
