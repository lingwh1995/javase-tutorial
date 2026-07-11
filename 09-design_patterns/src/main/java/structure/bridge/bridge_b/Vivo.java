package structure.bridge.bridge_b;

/**
 * @author lingwh
 * @desc Vivo手机
 * @date 2026/7/9 00:00
 */
public class Vivo implements Brand {
    @Override
    public void open() {
        System.out.println("Vivo手机开机");
    }

    @Override
    public void close() {
        System.out.println("Vivo手机关机");
    }

    @Override
    public void call() {
        System.out.println("Vivo手机打电话");
    }
}
