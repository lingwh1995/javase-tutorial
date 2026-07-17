package headfirst.designpatterns.singleton.threadsafe;

/**
 * 线程安全单例
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Singleton {

    private static Singleton uniqueInstance;

    // other useful instance variables here

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }

    // other useful methods here
    public String getDescription() {
        return "I'm a thread safe Singleton!";
    }
}
