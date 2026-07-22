package org.bluebridge.thread.section_03_thread_designpattern.producer_consumer.producer_consumer_c;

import java.util.Random;

/**
 * 做蛋糕线程
 *
 * @author lingwh
 * @date 2019/10/17 11:12
 */
public class MarkerThread extends Thread {

    private final Random random;
    private final Table table;

    /**
     * 蛋糕的流水号(所有糕点师共用)
     */
    private static int id = 0;

    public MarkerThread(String name, Table table, long seed) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));
                String cake = "[Cake No." + nextId() + " by " + Thread.currentThread().getName() + "]";
                table.put(cake);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static synchronized int nextId() {
        return id++;
    }
}
