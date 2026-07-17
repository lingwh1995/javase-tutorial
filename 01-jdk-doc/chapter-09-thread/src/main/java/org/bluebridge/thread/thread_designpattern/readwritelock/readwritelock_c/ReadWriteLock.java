package org.bluebridge.thread.thread_designpattern.readwritelock.readwritelock_c;

/**
 * 读写锁
 *
 * @author lingwh
 * @date 2019/10/18 13:37
 */
public class ReadWriteLock {

    /**
     * 实际正在读取中的线程个数
     */
    private int readingReaders = 0;

    /**
     * 正在等待写入的线程的个数
     */
    private int waitingWriter = 0;

    /**
     * 实际正在写入的线程的个数
     */
    private int writingWriters = 0;

    /**
     * 是否优先写入
     */
    private boolean preferWriter = true;

    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (waitingWriter > 0 && preferWriter)) {
            wait();
        }
        // 实际正在读取的线程个数+1
        readingReaders++;
    }

    public synchronized void readUnlock() {
        readingReaders--;
        preferWriter = true;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        // 正在等待写入的线程个数+1
        waitingWriter++;
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                wait();
            }
        } finally {
            // 正在等待写入的线程个数-1
            waitingWriter--;
        }
        // 实际正在写入线程个数+1
        writingWriters++;
    }

    public synchronized void writeUnlock() {
        writingWriters--;
        preferWriter = false;
        notifyAll();
    }
}
