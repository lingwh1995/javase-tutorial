package org.bluebridge.thread.thread_designpattern.workerthread;

/**
 * Worker Thread 模式 - 请求
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class Request {

    private final String name;
    private final int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " executed " + this.toString());
    }

    @Override
    public String toString() {
        return "Request{" + "name='" + name + '\'' + ", number=" + number + '}';
    }
}
