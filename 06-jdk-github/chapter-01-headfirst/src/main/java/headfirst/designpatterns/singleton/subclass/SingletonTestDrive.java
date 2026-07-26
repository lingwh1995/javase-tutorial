package headfirst.designpatterns.singleton.subclass;

/**
 * 单例测试类
 *
 * @author lingwh
 * @date 2023/12/7 08:48
 */
public class SingletonTestDrive {

    public static void main(String[] args) {
        Singleton foo = CoolerSingleton.getInstance();
        Singleton bar = HotterSingleton.getInstance();
        System.out.println(foo);
        System.out.println(bar);
    }
}
