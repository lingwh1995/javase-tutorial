package headfirst.designpatterns.singleton.subclass;

/**
 * @author lingwh
 * @desc 单例测试类
 * @date 2026/7/9 00:00
 */
public class SingletonTestDrive {
    public static void main(String[] args) {
        Singleton foo = CoolerSingleton.getInstance();
        Singleton bar = HotterSingleton.getInstance();
        System.out.println(foo);
        System.out.println(bar);
    }
}
