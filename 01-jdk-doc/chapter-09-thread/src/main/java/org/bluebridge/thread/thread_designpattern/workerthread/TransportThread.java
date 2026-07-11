package org.bluebridge.thread.thread_designpattern.workerthread;

import java.util.Random;

/**
 * @author lingwh
 * @desc Worker Thread 模式 - 传送线程
 * @date 2026/7/9 00:00
 */
public class TransportThread extends Thread {
    private final Channel channel;
    private static final Random random = new Random(System.currentTimeMillis());

    public TransportThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Request request = new Request(getName(), i);
                this.channel.put(request);
                Thread.sleep(random.nextInt(1_000));
            }
        } catch (Exception e) {

        }
    }
}
