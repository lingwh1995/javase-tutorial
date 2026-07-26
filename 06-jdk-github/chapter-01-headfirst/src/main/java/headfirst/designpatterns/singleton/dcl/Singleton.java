package headfirst.designpatterns.singleton.dcl;

/**
 * 双重检查锁单例
 *
 * Danger! This implementation of Singleton not
 * guaranteed to work prior to Java 5
 *
 * @author lingwh
 * @date 2023/12/7 10:46
 */
public class Singleton {

    private static volatile Singleton uniqueInstance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
