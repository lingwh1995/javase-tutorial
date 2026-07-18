package org.bluebridge.thread.thread_designpattern.producer_consumer.producer_consumer_c;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 桌子(基于ArrayBlockingQueue)
 *
 * @author lingwh
 * @date 2019/10/17 11:19
 */
public class Table extends ArrayBlockingQueue<String> {

    public Table(int count) {
        super(count);
    }

    @Override
    public void put(String cake) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "puts" + cake);
        super.put(cake);
    }

    @Override
    public String take() throws InterruptedException {
        String cake = super.take();
        System.out.println(Thread.currentThread().getName() + "take" + cake);
        return cake;
    }
}
