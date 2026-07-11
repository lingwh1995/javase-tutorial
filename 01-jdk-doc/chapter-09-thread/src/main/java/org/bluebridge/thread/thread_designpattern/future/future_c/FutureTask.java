package org.bluebridge.thread.thread_designpattern.future.future_c;

/**
 * @author lingwh
 * @desc Future 任务接口
 * @date 2026/7/9 00:00
 */
public interface FutureTask<T> {
    T call();
}
