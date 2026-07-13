package structure.adapter.adapter_d;

/**
 * 客户端 - 适配器模式
 *
 * @author lingwh
 * @date 2019/7/29 15:07
 */
public class Client {

    public static void main(String[] args) {
        AnimateListener listener = new AnimateOnStart();
        listener.onAnimateStart();
    }
}
