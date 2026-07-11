package org.bluebridge.thread.thread_designpattern.readwritelock.readwritelock_b;

/**
 * @author lingwh
 * @desc 读写锁
 * @date 2026/7/9 00:00
 */
public class ReadWriteLock {
    /**
     * 正在读的线程的个数
     */
    private int readingReaders = 0;

    /**
     * 正在等待读的线程的个数
     */
    private int watingReaders = 0;

    /**
     * 正在写的线程的个数
     */
    private int writingWriters = 0;

    /**
     * 正在等待写的线程的个数
     */
    private int waitingWriters = 0;

    /**
     * 为了避免一直读 那么给它一个默认更喜欢
     */
    private boolean preferWriter = true;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    /**
     * 读锁
     *
     * @throws InterruptedException
     */
    public synchronized void readLock() throws InterruptedException {
        this.watingReaders++;
        try {
            // 如果当前有线程在写,就不能读了
            while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
                this.wait();
            }
            this.readingReaders++;
        } finally {
            this.watingReaders--;
        }
    }

    /**
     * l 释放读锁
     */
    public synchronized void readUnlock() {
        this.readingReaders--;
        this.notifyAll();
    }

    /**
     * 写锁
     */
    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                this.wait();
            }
            this.writingWriters++;
        } finally {
            this.waitingWriters--;
        }
    }

    /**
     * 释放写锁
     */
    public synchronized void writeUnlock() {
        this.writingWriters--;
        this.notifyAll();
    }
}
