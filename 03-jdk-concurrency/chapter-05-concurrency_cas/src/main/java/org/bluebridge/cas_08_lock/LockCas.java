package org.bluebridge.cas_08_lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lingwh
 * @desc 基于CAS实现的锁
 * @date 2026/7/9 00:00
 */
public class LockCas {

    private AtomicInteger state = new AtomicInteger(0);

    public void lock() {
        while (true) {
            System.out.println("Thread " + Thread.currentThread().getName() + " 自旋中......");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 核心代码
            if (state.compareAndSet(0, 1)) {
                break;
            }
        }
    }

    public void unlock() {
        System.out.println("Thread " + Thread.currentThread().getName() + " unlock......");
        state.set(0);
    }
}
