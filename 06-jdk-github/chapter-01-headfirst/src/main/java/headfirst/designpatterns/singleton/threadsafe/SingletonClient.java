package headfirst.designpatterns.singleton.threadsafe;

/**
 * 单例客户端
 *
 * @author lingwh
 * @date 2023/12/7 08:15
 */
public class SingletonClient {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.getDescription());
    }
}
