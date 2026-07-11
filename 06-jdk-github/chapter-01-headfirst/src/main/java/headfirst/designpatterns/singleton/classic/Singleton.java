package headfirst.designpatterns.singleton.classic;

/**
 * 经典单例
 *
 * NOTE: This is not thread safe!
 *
 * @author lingwh
 * @desc 经典单例
 * @date 2026/7/9 00:00
 */
public class Singleton {
    private static Singleton uniqueInstance;

    private Singleton() {}

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
