package org.bluebridge.thread.section_03_thread_designpattern.observer;

import java.util.List;

/**
 * 线程生命周期观察者
 *
 * @author lingwh
 * @date 2026/7/13 16:29
 */
public class ThreadLifeCycleObserver implements LifeCycleListener {

    private static final Object LOCK = new Object();

    public void currentQuery(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        ids.stream().forEach(id -> new Thread(new ObservableRunnable(this) {
            @Override
            public void run() {
                try {
                    notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                    System.out.println("query for the id" + id);
                    Thread.sleep(1000L);
                    int x = 1 / 0;
                    notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                } catch (Exception e) {
                    notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                }
            }
        }, id).start());
    }

    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {
        synchronized (LOCK) {
            System.out.println(
                    "The runnable ["
                            + event.getThread().getName()
                            + "] data changed and state is ["
                            + event.getState()
                            + "]");
            if (event.getCause() != null) {
                System.out.println("The runnable [" + event.getThread().getName() + "] process failed.");
                event.getCause().printStackTrace();
            }
        }
    }
}
