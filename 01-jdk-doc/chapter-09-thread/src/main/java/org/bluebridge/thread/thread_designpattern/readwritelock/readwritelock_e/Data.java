package org.bluebridge.thread.thread_designpattern.readwritelock.readwritelock_e;

import java.util.Arrays;

/**
 * 共享数据
 *
 * @author lingwh
 * @date 2019/10/18 11:32
 */
public class Data {

    private final char[] buffer;

    public Data(int size) {
        this.buffer = new char[size];
        Arrays.fill(buffer, '*');
    }

    public synchronized char[] read() throws InterruptedException {
        return doRead();
    }

    public synchronized void write(char c) throws InterruptedException {
        doWrite(c);
    }

    private char[] doRead() {
        char[] newBuffer = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newBuffer[i] = buffer[i];
        }
        slowly();
        return newBuffer;
    }

    private void doWrite(char c) {
        System.out.println("当前写入的字符:" + c);
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly();
        }
    }

    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
