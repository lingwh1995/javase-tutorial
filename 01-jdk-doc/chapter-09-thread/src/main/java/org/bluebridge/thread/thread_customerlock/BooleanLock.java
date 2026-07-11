package org.bluebridge.thread.thread_customerlock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * @author lingwh
 * @desc 布尔锁实现
 * @date 2026/7/9 00:00
 */
public class BooleanLock implements Lock {

    private boolean initValue = false;
    private Collection<Thread> blockLockContainers = new ArrayList<>();
    private Thread currentTread;

    public BooleanLock() {
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            blockLockContainers.add(Thread.currentThread());
            this.wait();
        }
        blockLockContainers.remove(Thread.currentThread());
        this.initValue = true;
        currentTread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws TimeOutExpection, InterruptedException {
        if (mills <= 0) {
            lock();
        }
        long reallyTimeOut = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (initValue) {
            if (reallyTimeOut <= 0) {
                throw new TimeOutExpection("Time out ......");
            }
            blockLockContainers.add(Thread.currentThread());
            this.wait(mills);
            reallyTimeOut = endTime - System.currentTimeMillis();
            System.out.println("reallyTimeOut:" + reallyTimeOut);
        }
        this.initValue = true;
        this.currentTread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if (Thread.currentThread() == currentTread) {
            this.initValue = false;
            Optional.of(Thread.currentThread().getName() + ":release the monitor......")
                    .ifPresent(System.out::println);
            this.notifyAll();
        }
    }

    /**
     * 只读的操作
     *
     * @return
     */
    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockLockContainers);
    }

    @Override
    public int getBlockedThreadSize() {
        return blockLockContainers.size();
    }
}
