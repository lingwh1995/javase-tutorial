package org.bluebridge.thread.thread_designpattern.future.future_c;

/**
 * Future 任务接口
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public interface FutureTask<T> {

    T call();
}
