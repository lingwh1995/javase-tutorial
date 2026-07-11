package org.bluebridge.thread.thread_designpattern.readwritelock.readwritelock_a;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author lingwh
 * @desc 客户端
 * @date 2019/10/15 17:44
 */
public class Client {
    public static void main(String[] args) {
        // final List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        final List<Integer> list = new CopyOnWriteArrayList<>();
        new WriteThread(list).start();
        new ReadThread(list).start();
    }
}
