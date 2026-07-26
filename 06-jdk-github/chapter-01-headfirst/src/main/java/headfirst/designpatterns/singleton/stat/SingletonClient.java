package headfirst.designpatterns.singleton.stat;

/**
 * 单例客户端
 *
 * @author lingwh
 * @date 2023/12/7 09:55
 */
public class SingletonClient {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.getDescription());
    }
}
