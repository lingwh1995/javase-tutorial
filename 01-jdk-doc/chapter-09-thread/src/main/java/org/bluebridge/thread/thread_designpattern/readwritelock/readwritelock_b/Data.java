package org.bluebridge.thread.thread_designpattern.readwritelock.readwritelock_b;

/**
 * 已共享的数据
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class Data {

    private final char[] buffer;
    private final ReadWriteLock lock = new ReadWriteLock();

    public Data(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < size; i++) {
            this.buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            lock.readLock();
            return this.doRead();
        } finally {
            lock.readUnlock();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            lock.writeLock();
            this.doWrite(c);
        } finally {
            lock.writeUnlock();
        }
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly(10);
        }
    }

    private char[] doRead() {
        char[] newBuffer = new char[buffer.length];
        for (int i = 0; i < newBuffer.length; i++) {
            newBuffer[i] = buffer[i];
        }
        slowly(50);
        return buffer;
    }

    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
