package org.bluebridge.thread.section_03_thread_designpattern.guarded_suspension.guarded_suspension_c;

import java.util.Random;

/**
 * 客户端线程
 *
 * @author lingwh
 * @date 2019/10/16 09:23
 */
public class ClientThread extends Thread {

    private final Random random;
    private final RequestQueue requestQueue;

    public ClientThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Request request = new Request("No." + i);
            System.out.println(Thread.currentThread().getName() + " requests " + request);
            requestQueue.putRequest(request);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
