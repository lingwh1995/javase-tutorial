package org.bluebridge.thread.thread_designpattern.observer;

/**
 * @author lingwh
 * @desc 生命周期监听器接口
 * @date 2026/7/9 00:00
 */
public interface LifeCycleListener {
    void onEvent(ObservableRunnable.RunnableEvent event);
}
