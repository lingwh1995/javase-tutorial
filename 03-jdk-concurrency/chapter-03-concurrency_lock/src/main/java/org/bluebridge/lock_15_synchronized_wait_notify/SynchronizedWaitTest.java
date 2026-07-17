package org.bluebridge.lock_15_synchronized_wait_notify;

/**
 * wait()会释放锁，但是也会有阻塞效果
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public class SynchronizedWaitTest {

    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread " + Thread.currentThread().getName() + " 执行wait()方法之前......");
                try {
                    // 让线程在lock上等待3秒
                    lock.wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread " + Thread.currentThread().getName() + " 执行wait()方法之后......");
            }
        }, "t1").start();
    }
}
