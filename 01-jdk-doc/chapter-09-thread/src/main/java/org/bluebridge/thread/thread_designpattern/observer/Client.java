package org.bluebridge.thread.thread_designpattern.observer;

import java.util.Arrays;

/**
 * @author lingwh
 * @desc 客户端
 * @date 2026/7/9 00:00
 */
public class Client {
    public static void main(String[] args) {
        new ThreadLifeCycleObserver().currentQuery(Arrays.asList("1", "2"));
    }
}
