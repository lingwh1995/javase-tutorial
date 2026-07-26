package cn.itcast.n3;

/**
 * CPU 占用测试
 *
 * @author lingwh
 * @date 2025/2/7 08:15
 */
public class TestCpu {

    public static void main(String[] args) {
        new Thread(() -> {
            while(true) {
                /*try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }).start();
    }
}
