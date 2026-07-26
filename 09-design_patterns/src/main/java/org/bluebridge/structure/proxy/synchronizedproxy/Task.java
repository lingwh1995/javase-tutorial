package org.bluebridge.structure.proxy.synchronizedproxy;

/**
 * 任务接口
 *
 * @author lingwh
 * @date 2026/7/22 08:20
 */
public interface Task {

    void task();

    Thread createThread1();

    Thread createThread2();
}
