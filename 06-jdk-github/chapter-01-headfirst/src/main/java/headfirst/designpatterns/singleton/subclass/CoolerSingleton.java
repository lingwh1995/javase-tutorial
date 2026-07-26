package headfirst.designpatterns.singleton.subclass;

/**
 * 冷却器单例
 *
 * @author lingwh
 * @date 2023/12/7 09:38
 */
public class CoolerSingleton extends Singleton {

    // useful instance variables here
    protected static Singleton uniqueInstance;

    private CoolerSingleton() {
        super();
    }

    // useful methods here
}
