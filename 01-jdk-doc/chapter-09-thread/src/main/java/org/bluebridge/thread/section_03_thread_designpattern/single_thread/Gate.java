package org.bluebridge.thread.section_03_thread_designpattern.single_thread;

/**
 * 单线程执行模式 - 门
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class Gate {

    private int counter = 0;
    private String name = "Nobody";
    private String address = "NoWhere";

    public synchronized void pass(String name, String address) {
        counter++;
        this.name = name;
        this.address = address;
        verify();
    }

    private void verify() {
        if (this.name.charAt(0) != this.address.charAt(0)) {
            System.out.println(counter + "**********BROKEN**********" + this.toString());
        }
    }

    @Override
    public synchronized String toString() {
        return "Gate{" + "name='" + name + '\'' + ", address='" + address + '\'' + '}';
    }

    /**
     * 注意：加上下面两个方法，Gate 类就不安全了 因为 String name，String address 必须合在一起赋值才能有保护意义
     */
    // public synchronized void setName(String name) {
    // this.name = name;
    // }

    // public synchronized void setAddress(String address) {
    // this.address = address;
    // }
}
