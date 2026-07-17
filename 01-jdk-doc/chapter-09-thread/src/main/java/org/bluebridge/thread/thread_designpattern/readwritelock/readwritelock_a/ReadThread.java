package org.bluebridge.thread.thread_designpattern.readwritelock.readwritelock_a;

import java.util.List;

/**
 * 读线程
 *
 * @author lingwh
 * @date 2019/10/15 17:42
 */
public class ReadThread extends Thread {

    private final List<Integer> list;

    public ReadThread(List<Integer> list) {
        super("ReadThread");
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                for (Integer i : list) {
                    System.out.println(i);
                }
            }
        }
    }
}
