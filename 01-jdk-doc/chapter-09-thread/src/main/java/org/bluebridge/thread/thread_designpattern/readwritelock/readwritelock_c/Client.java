package org.bluebridge.thread.thread_designpattern.readwritelock.readwritelock_c;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2019/10/18 13:54
 */
public class Client {

    public static void main(String[] args) {
        Data data = new Data(10);
        new ReadThread(data).start();
        new ReadThread(data).start();
        new ReadThread(data).start();
        new ReadThread(data).start();
        new WriteThread(data, "ABCDEFGHIJKLMNOPQRST").start();
        new WriteThread(data, "abcdefghijklmnopqrst").start();
    }
}
