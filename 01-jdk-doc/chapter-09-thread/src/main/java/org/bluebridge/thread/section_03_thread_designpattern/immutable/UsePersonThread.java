package org.bluebridge.thread.section_03_thread_designpattern.immutable;

/**
 * 使用 Person 的线程
 *
 * @author lingwh
 * @date 2026/4/23 10:30
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
