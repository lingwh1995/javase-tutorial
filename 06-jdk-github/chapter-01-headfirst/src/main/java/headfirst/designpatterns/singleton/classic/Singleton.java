package headfirst.designpatterns.singleton.classic;

/**
 * 经典单例
 *
 * NOTE: This is not thread safe!
 *
 * @author lingwh
 * @date 2023/12/7 11:20
 */
public class Singleton {

    private static Singleton uniqueInstance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }

    // other useful methods here
    public String getDescription() {
        return "I'm a classic Singleton!";
    }
}
