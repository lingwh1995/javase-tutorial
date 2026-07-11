package org.bluebridge.thread.thread_designpattern.future.future_c;

import java.util.function.Consumer;

/**
 * @author lingwh
 * @desc Future 模式服务
 * @date 2026/7/9 00:00
 */
public class FutureService {

    public <T> Future<T> submit(final FutureTask<T> task, Consumer<T> consumer) {
        final AsynFuture<T> asynFuture = new AsynFuture<>();
        new Thread(()->{
            T result = task.call();
            asynFuture.done(result);
            consumer.accept(result);
        }).start();
        return asynFuture;
    }
}
