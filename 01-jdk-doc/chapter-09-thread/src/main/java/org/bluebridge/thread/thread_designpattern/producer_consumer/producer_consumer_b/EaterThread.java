package org.bluebridge.thread.thread_designpattern.producer_consumer.producer_consumer_b;

import java.util.Random;

/**
 * @author lingwh
 * @desc 吃蛋糕线程
 * @date 2019/10/17 11:17
 */
public class EaterThread extends Thread {
    private final Random random;
    private final Table table;

    public EaterThread(String name, Table table, long seed) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String cake = table.take();
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
