package org.bluebridge.thread.thread_designpattern.future.future_c;

/**
 * 异步 Future 实现
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class AsynFuture<T> implements Future<T> {

    /**
     * 判断任务有没有执行完成
     */
    private volatile boolean isDone = false;

    private T result;

    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            while (!isDone) {
                this.wait();
            }
        }
        return result;
    }

    public void done(T result) {
        synchronized (this) {
            this.result = result;
            this.isDone = true;
            this.notifyAll();
        }
    }
}
