package cn.itcast.n3;

/**
 * @author lingwh
 * @desc CPU占用测试
 * @date 2026/7/9 00:00
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
