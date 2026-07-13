package org.bluebridge.thread.thread_designpattern.single_thread;

/**
 * 单线程执行模式 - 客户端
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class Client {

    public static void main(String[] args) {
        Gate gate = new Gate();
        new UserThread(gate, "Alice", "Alas").start();
        new UserThread(gate, "Bobby", "Brazli").start();
        new UserThread(gate, "Chirs", "Canda").start();
    }
}
