package headfirst.designpatterns.singleton.classic;

/**
 * 单例客户端
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class SingletonClient {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.getDescription());
    }
}
