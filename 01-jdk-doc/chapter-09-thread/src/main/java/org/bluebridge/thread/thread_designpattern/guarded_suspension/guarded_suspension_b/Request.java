package org.bluebridge.thread.thread_designpattern.guarded_suspension.guarded_suspension_b;

/**
 * @author lingwh
 * @desc 请求
 * @date 2019/10/16 9:00
 */
public class Request {
    private final String name;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "[Request " + name + " ]";
    }
}
