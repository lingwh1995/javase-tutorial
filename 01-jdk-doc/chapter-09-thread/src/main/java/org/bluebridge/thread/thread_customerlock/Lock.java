package org.bluebridge.thread.thread_customerlock;

import java.util.Collection;

/**
 * 锁接口
 *
 * @author lingwh
 * @date 2026/4/23 16:29
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
