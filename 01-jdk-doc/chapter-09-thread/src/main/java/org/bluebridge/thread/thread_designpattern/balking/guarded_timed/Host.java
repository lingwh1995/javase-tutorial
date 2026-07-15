package org.bluebridge.thread.thread_designpattern.balking.guarded_timed;

import java.util.concurrent.TimeoutException;

/**
 * Guarded Timed 模式主机
 *
 * @author lingwh
 * @date 2019/10/17 10:36
 */
public class Host {

    /**
     * 超时时间
     */
    private final long timeout;

    /**
     * 方法正常执行时值为true
     */
    private boolean ready = false;

    public Host(long timeout) {
        this.timeout = timeout;
    }

    public synchronized void setExecutable(boolean on) {
        ready = on;
        notifyAll();
    }

    public synchronized void execute() throws InterruptedException, TimeoutException {
        long start = System.currentTimeMillis();
        while (!ready) {
            // 当前时间
            long now = System.currentTimeMillis();
            // 剩余的等待时间
            long rest = timeout - (now - start);
            if (rest <= 0) {
                throw new TimeoutException("now -start = " + (now - start) + ",timeout = " + timeout);
            }
            wait(rest);
            doExexute();
        }
    }

    /**
     * 实际的处理
     */
    private void doExexute() {
        System.out.println(Thread.currentThread().getName() + " calls doExecute");
    }
}
