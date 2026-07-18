package cn.itcast.n4.reentrant;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁测试
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TestFair {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(false);

        lock.lock();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " running...");
                } finally {
                    lock.unlock();
                }
            }, "t" + i).start();
        }

        // 1s 之后去争抢锁
        Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " running...");
                } finally {
                    lock.unlock();
                }
            }, "强行插入").start();
        }
        lock.unlock();
    }
}
