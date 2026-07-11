package headfirst.designpatterns.singleton.subclass;

/**
 * @author lingwh
 * @desc 可子类化的单例
 * @date 2026/7/9 00:00
 */
public class Singleton {
    protected static Singleton uniqueInstance;

    // other useful instance variables here

    protected Singleton() {}

    public static synchronized Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }

    // other useful methods here
}
