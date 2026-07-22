package org.bluebridge.my_aqs_lock.unit_01_v1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义锁
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class MyLock implements Lock {

    private static MySync sync = new MySync();

    // 尝试，不成功，进入等待队列
    @Override
    public void lock() {
        sync.acquire(1);
    }

    // 尝试，不成功，进入等待队列，可打断
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    // 尝试一次，不成功返回，不进入队列
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    // 尝试，不成功，进入等待队列，有时限
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    // 释放锁
    @Override
    public void unlock() {
        sync.release(1);
    }

    // 生成条件变量
    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
