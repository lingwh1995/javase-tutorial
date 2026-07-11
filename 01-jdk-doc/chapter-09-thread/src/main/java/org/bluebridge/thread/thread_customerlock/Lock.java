package org.bluebridge.thread.thread_customerlock;

import java.util.Collection;

/**
 * @author lingwh
 * @desc 锁接口
 * @date 2026/7/9 00:00
 */
public interface Lock {
    public class TimeOutExpection extends Exception {
        public TimeOutExpection(String message) {
            super(message);
        }
    }

    void lock() throws InterruptedException;

    void lock(long timeout) throws TimeOutExpection, InterruptedException;

    void unlock() throws InterruptedException;

    Collection<Thread> getBlockedThread();

    int getBlockedThreadSize();
}
