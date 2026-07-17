package org.bluebridge.thread.thread_designpattern.guarded_suspension.guarded_suspension_a;

import java.util.Random;

/**
 * 服务器线程
 *
 * @author lingwh
 * @date 2019/10/16 9:29
 */
public class ServerThread extends Thread {

    private final Random random;
    private final RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request = requestQueue.getRequest();
            System.out.println(Thread.currentThread().getName() + " handles " + request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
