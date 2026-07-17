package headfirst.designpatterns.singleton.subclass;

/**
 * 单例测试类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class SingletonTestDrive {

    public static void main(String[] args) {
        Singleton foo = CoolerSingleton.getInstance();
        Singleton bar = HotterSingleton.getInstance();
        System.out.println(foo);
        System.out.println(bar);
    }
}
