package org.bluebridge.thread.thread_designpattern.workerthread;

/**
 * @author lingwh
 * @desc Worker Thread 模式 - 请求
 * @date 2026/7/9 00:00
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
