package cn.itcast.n4;

import java.io.IOException;
/**
 * PrintConcurrentLocks
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            synchronized (Test.class) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        Thread.sleep(100);
        new Thread(()->{
            synchronized (Test.class) {

            }
        },"t2").start();

        new Thread(()->{
            synchronized (Test.class) {

            }
        },"t3").start();
    }
}
