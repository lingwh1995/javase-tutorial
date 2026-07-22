package org.bluebridge.thread.section_03_thread_designpattern.observer;

/**
 * 生命周期监听器接口
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public interface LifeCycleListener {

    void onEvent(ObservableRunnable.RunnableEvent event);
}
