package org.bluebridge.thread.section_03_thread_designpattern.workerthread;

/**
 * Worker Thread 模式 - 客户端
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class WorkClient {

    public static void main(String[] args) {
        final Channel channel = new Channel(5);
        channel.startWorker();

        new TransportThread("Alex", channel).start();
        new TransportThread("Jack", channel).start();
        new TransportThread("William", channel).start();
    }
}
