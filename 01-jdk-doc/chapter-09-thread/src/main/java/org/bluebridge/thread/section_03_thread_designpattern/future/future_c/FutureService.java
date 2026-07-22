package org.bluebridge.thread.section_03_thread_designpattern.future.future_c;

import java.util.function.Consumer;

/**
 * Future 模式服务
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class FutureService {

    public <T> Future<T> submit(final FutureTask<T> task, Consumer<T> consumer) {
        final AsynFuture<T> asynFuture = new AsynFuture<>();
        new Thread(() -> {
            T result = task.call();
            asynFuture.done(result);
            consumer.accept(result);
        }).start();
        return asynFuture;
    }
}
