package org.bluebridge.thread.thread_designpattern.future.future_b;

/**
 * @author lingwh
 * @desc 异步 Future 实现
 * @date 2026/7/9 00:00
 */
public class AsynFuture<T> implements Future<T> {

    /**
     * 判断任务有没有执行完成
     */
    private volatile boolean isDone = false;

    private T result;

    @Override
    public T get() throws InterruptedException {
        synchronized(this){
            while(!isDone){
                this.wait();
            }
        }
        return result;
    }

    public void done(T result) {
        synchronized(this) {
            this.result = result;
            this.isDone = true;
            this.notifyAll();
        }
    }
}
