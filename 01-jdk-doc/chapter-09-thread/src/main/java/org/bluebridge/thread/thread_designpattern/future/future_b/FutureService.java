package org.bluebridge.thread.thread_designpattern.future.future_b;

/**
 * Future 模式服务
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class FutureService {

    public <T> Future<T> submit(final FutureTask<T> task) {
        final AsynFuture<T> asynFuture = new AsynFuture<>();
        new Thread(() -> {
            T result = task.call();
            asynFuture.done(result);
        }).start();
        return asynFuture;
    }
}
