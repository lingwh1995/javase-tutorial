package org.bluebridge.thread.section_03_thread_designpattern.producer_consumer.producer_consumer_b;

/**
 * 桌子(蛋糕缓冲区)
 *
 * @author lingwh
 * @date 2019/10/17 11:19
 */
public class Table {

    private final String[] buffer;

    /**
     * 下次put的位置
     */
    private int tail;

    /**
     * 下次take的位置
     */
    private int head;

    /**
     * buffer中蛋糕的个数
     */
    private int count;

    public Table(int count) {
        this.buffer = new String[count];
        this.tail = 0;
        this.head = 0;
        this.count = 0;
    }

    public synchronized void put(String cake) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " puts " + cake);
        while (count >= buffer.length) {
            wait();
        }
        buffer[tail] = cake;
        tail = (tail + 1) % buffer.length;
        count++;
        notify();
        // notifyAll();
    }

    public synchronized String take() throws InterruptedException {
        while (count <= 0) {
            wait();
        }
        String cake = buffer[head];
        head = (head + 1) % buffer.length;
        count--;
        notify();
        // notifyAll();
        System.out.println(Thread.currentThread().getName() + " takes " + cake);
        return cake;
    }
}
