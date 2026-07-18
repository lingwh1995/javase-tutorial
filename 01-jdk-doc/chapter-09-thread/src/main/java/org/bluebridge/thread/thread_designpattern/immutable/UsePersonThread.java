package org.bluebridge.thread.thread_designpattern.immutable;

/**
 * 使用Person的线程
 *
 * @author lingwh
 * @date 2026/7/13 16:29
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
