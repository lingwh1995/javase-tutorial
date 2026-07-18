package cn.itcast.n3;

/**
 * CPU占用测试
 *
 * @author lingwh
 * @date 2026/7/13 19:02
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
