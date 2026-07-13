package org.bluebridge.thread.thread_designpattern.observer;

import java.util.Arrays;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class Client {

    public static void main(String[] args) {
        new ThreadLifeCycleObserver().currentQuery(Arrays.asList("1", "2"));
    }
}
