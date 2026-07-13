package org.bluebridge.thread.thread_designpattern.readwritelock.readwritelock_b;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class Client {

    public static void main(String[] args) {
        final Data sharedData = new Data(10);
        new ReaderThread(sharedData).start();
        new ReaderThread(sharedData).start();
        new ReaderThread(sharedData).start();
        new ReaderThread(sharedData).start();
        new ReaderThread(sharedData).start();
        new WriteThread(sharedData, "123456789").start();
        new WriteThread(sharedData, "ABCEFG").start();
    }
}
