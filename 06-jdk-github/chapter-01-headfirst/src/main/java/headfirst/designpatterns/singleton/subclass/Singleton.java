package headfirst.designpatterns.singleton.subclass;

/**
 * 可子类化的单例
 *
 * @author lingwh
 * @date 2023/12/7 09:05
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
