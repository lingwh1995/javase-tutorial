package org.bluebridge.thread.thread_designpattern.future.future_b;

/**
 * @author lingwh
 * @desc Future 接口
 * @date 2026/7/9 00:00
 */
public interface Future<T> {
    T get() throws InterruptedException;
}
