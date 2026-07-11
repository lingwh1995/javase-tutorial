package headfirst.designpatterns.singleton.stat;

/**
 * @author lingwh
 * @desc 静态初始化单例
 * @date 2026/7/9 00:00
 */
public class Singleton {
    private static Singleton uniqueInstance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return uniqueInstance;
    }

    // other useful methods here
    public String getDescription() {
        return "I'm a statically initialized Singleton!";
    }
}
