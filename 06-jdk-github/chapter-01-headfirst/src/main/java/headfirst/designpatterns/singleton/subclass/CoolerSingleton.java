package headfirst.designpatterns.singleton.subclass;

/**
 * @author lingwh
 * @desc 冷却器单例
 * @date 2026/7/9 00:00
 */
public class CoolerSingleton extends Singleton {
    // useful instance variables here
    protected static Singleton uniqueInstance;

    private CoolerSingleton() {
        super();
    }

    // useful methods here
}
