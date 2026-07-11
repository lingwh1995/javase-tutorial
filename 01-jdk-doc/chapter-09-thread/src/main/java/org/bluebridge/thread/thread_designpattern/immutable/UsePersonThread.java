package org.bluebridge.thread.thread_designpattern.immutable;

/**
 * @author lingwh
 * @desc 使用Person的线程
 * @date 2026/7/9 00:00
 */
public class UsePersonThread extends Thread {
    private Person person;

    public UsePersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " print " + person.toString());
        }
    }
}
