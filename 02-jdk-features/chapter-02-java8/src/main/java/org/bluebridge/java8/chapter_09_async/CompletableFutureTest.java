package org.bluebridge.java8.chapter_09_async;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture异步编程测试
 *
 * @author lingwh
 * @date 2026/6/22 15:10
 */
@Slf4j
public class CompletableFutureTest {

    @Test
    public void testCompletableFuture() {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(String::toUpperCase);

        try {
            String result = future.get();
            log.info("result: {}", result);
        } catch (Exception e) {
            log.error("error: {}", e.getMessage(), e);
        }
    }
}
