package org.bluebridge.thread.thread_designpattern.future.future_b;

/**
 * Future 接口
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public interface Future<T> {

    T get() throws InterruptedException;
}
