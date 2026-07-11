package headfirst.designpatterns.singleton.threadsafe;

/**
 * @author lingwh
 * @desc 单例客户端
 * @date 2026/7/9 00:00
 */
public class SingletonClient {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.getDescription());
    }
}
