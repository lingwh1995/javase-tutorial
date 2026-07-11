package org.bluebridge.thread.thread_designpattern.countdown;

/**
 * @author lingwh
 * @desc 自定义倒计数器
 * @date 2026/7/9 00:00
 */
public class CustomerCountDown {
    private final int total;
    private int counter;

    public CustomerCountDown(int total) {
        this.total = total;
    }

    public void countDown() {
        synchronized (this) {
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (counter != total) {
                this.wait();
            }
        }
    }
}
