package org.bluebridge.thread.section_03_thread_designpattern.producer_consumer.producer_consumer_c;

import java.util.Random;

/**
 * 吃蛋糕线程
 *
 * @author lingwh
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
