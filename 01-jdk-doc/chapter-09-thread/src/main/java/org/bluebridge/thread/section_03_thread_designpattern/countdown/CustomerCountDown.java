package org.bluebridge.thread.section_03_thread_designpattern.countdown;

/**
 * 自定义倒计数器
 *
 * @author lingwh
 * @date 2026/4/23 16:29
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
