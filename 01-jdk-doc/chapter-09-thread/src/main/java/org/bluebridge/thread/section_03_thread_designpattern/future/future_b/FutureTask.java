package org.bluebridge.thread.section_03_thread_designpattern.future.future_b;

/**
 * Future 任务接口
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public interface FutureTask<T> {

    T call();
}
