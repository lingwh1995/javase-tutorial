package headfirst.designpatterns.singleton.subclass;

/**
 * 冷却器单例
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class CoolerSingleton extends Singleton {

    // useful instance variables here
    protected static Singleton uniqueInstance;

    private CoolerSingleton() {
        super();
    }

    // useful methods here
}
