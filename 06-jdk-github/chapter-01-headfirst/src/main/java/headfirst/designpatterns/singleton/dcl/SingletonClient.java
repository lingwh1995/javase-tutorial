package headfirst.designpatterns.singleton.dcl;

/**
 * 单例客户端
 *
 * @author lingwh
 * @date 2023/12/7 10:29
 */
public class SingletonClient {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
    }
}
