package headfirst.designpatterns.singleton.subclass;

/**
 * 可子类化的单例
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Singleton {

    protected static Singleton uniqueInstance;

    // other useful instance variables here

    protected Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }

    // other useful methods here
}
