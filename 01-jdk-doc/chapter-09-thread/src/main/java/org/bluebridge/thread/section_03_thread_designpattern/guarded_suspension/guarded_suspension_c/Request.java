package org.bluebridge.thread.section_03_thread_designpattern.guarded_suspension.guarded_suspension_c;

/**
 * 请求
 *
 * @author lingwh
 * @date 2019/10/16 09:00
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
