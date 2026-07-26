package headfirst.designpatterns.singleton.stat;

/**
 * 静态初始化单例
 *
 * @author lingwh
 * @date 2023/12/7 10:12
 */
public class Singleton {

    private static Singleton uniqueInstance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return uniqueInstance;
    }

    // other useful methods here
    public String getDescription() {
        return "I'm a statically initialized Singleton!";
    }
}
