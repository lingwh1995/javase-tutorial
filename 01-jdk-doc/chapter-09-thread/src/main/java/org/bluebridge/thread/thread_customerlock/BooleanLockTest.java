package org.bluebridge.thread.thread_customerlock;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 布尔锁测试
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class BooleanLockTest {

    public static void main(String[] args) {
        // testLock();
        testTimeOutLock();
    }

    /**
     * 测试显示锁
     */
    private static void testLock() {
        final BooleanLock lock = new BooleanLock();
        Stream.of("T1", "T2", "T3", "T4").forEach(name -> new Thread(() -> {
            try {
                lock.lock();
                Optional.of(Thread.currentThread().getName() + ":use the monitor to lock......")
                        .ifPresent(System.out::println);
                work();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, name).start());
    }

    /**
     * 测试显式超市锁
     */
    private static void testTimeOutLock() {
        final BooleanLock lock = new BooleanLock();
        Stream.of("T1", "T2", "T3", "T4").forEach(name -> new Thread(() -> {
            try {
                lock.lock(10L);
                Optional.of(Thread.currentThread().getName() + ":use the monitor to lock......")
                        .ifPresent(System.out::println);
                work();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Lock.TimeOutExpection timeOutExpection) {
                timeOutExpection.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, name).start());
    }

    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + ":is working......")
                .ifPresent(System.out::println);
        Thread.sleep(1000);
    }
}
