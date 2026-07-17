package org.bluebridge.thread_04_sleep;

/**
 * 线程睡眠
 *
 * 1. Thread.sleep(1000)，这行代码写在哪个线程里，就是哪个线程睡眠
 * 2. 调用 sleep 会让当前线程从 Running 进入 Timed Waiting 状态（阻塞）
 * 3. 其它线程可以使用 interrupt 方法打断正在睡眠的线程，这时 sleep 方法会抛出 InterruptedException
 * 4. 睡眠结束后的线程未必会立刻得到执行
 * 5. 建议用 TimeUnit 的 sleep 代替 Thread 的 sleep 来获得更好的可读性
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class SleepTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + " 进入睡眠...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread " + Thread.currentThread().getName() + " 被唤醒...");
                throw new RuntimeException(e);
            }
        }, "t1");

        System.out.println("当前线程" + t.getName() + "状态: " + t.getState());

        t.start();

        System.out.println("当前线程" + t.getName() + "状态: " + t.getState());

        Thread.sleep(500);

        System.out.println("Thread t1 即将被唤醒...");
        t.interrupt();

        System.out.println("当前线程" + t.getName() + "状态: " + t.getState());

        System.out.println("Thread " + Thread.currentThread().getName() + " thread is running...");
    }
}
