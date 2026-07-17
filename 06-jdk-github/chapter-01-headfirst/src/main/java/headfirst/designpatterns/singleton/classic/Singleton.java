package headfirst.designpatterns.singleton.classic;

/**
 * 经典单例
 *
 * NOTE: This is not thread safe!
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
