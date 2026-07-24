package org.bluebridge.thread.section_03_thread_designpattern.single_thread;

/**
 * 单线程执行模式 - 用户线程
 *
 * @author lingwh
 * @date 2026/4/23 10:30
 */
public class UserThread extends Thread {

    private final String name;
    private final String address;
    private final Gate gate;

    public UserThread(Gate gate, String name, String address) {
        this.gate = gate;
        this.name = name;
        this.address = address;
    }

    @Override
    public void run() {
        System.out.println(this.name + " BEGIN......");
        while (true) {
            this.gate.pass(this.name, this.address);
        }
    }
}
