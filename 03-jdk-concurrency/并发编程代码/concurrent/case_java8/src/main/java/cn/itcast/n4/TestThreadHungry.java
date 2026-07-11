package cn.itcast.n4;

import static cn.itcast.n2.util.Sleeper.sleep;

/**
 * @author lingwh
 * @desc 线程饥饿测试
 * @date 2026/7/9 00:00
 */
public class TestThreadHungry {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(() -> {
                while(true){

                }
            });
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();
        }

        Thread.sleep(1000);
        Thread me = new Thread(() -> {
            System.out.println("done");
        });
        me.setPriority(Thread.MIN_PRIORITY);
        me.start();
    }
}
