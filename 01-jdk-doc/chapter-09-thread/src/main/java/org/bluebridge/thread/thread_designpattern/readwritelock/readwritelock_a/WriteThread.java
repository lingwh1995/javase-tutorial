package org.bluebridge.thread.thread_designpattern.readwritelock.readwritelock_a;

import java.util.List;

/**
 * @author lingwh
 * @desc 写线程
 * @date 2019/10/15 17:38
 */
public class WriteThread extends Thread {
    private final List<Integer> list;

    public WriteThread(List<Integer> list) {
        super("WriteThread");
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; true; i++) {
            list.add(i);
            list.remove(0);
        }
    }
}
