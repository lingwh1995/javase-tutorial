package org.bluebridge.thread.thread_designpattern.single_thread;

/**
 * @author lingwh
 * @desc 单线程执行模式 - 客户端
 * @date 2026/7/9 00:00
 */
public class Client {
    public static void main(String[] args) {
        Gate gate = new Gate();
        new UserThread(gate, "Alice", "Alas").start();
        new UserThread(gate, "Bobby", "Brazli").start();
        new UserThread(gate, "Chirs", "Canda").start();
    }
}
