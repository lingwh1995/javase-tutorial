﻿package org.bluebridge.section_09_jdk9.unit_07_completable_future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Java9 CompletableFuture 改进测试
 *
 * Java9 对 CompletableFuture 进行了增强, 新增了以下方法:
 * 1. CompletableFuture.completeAsync(Supplier): 使用 ForkJoinPool 异步完成
 * 2. CompletableFuture.completeAsync(Supplier, Executor): 使用指定线程池异步完成
 * 3. CompletableFuture.orTimeout(long, TimeUnit): 超时则抛出 TimeoutException
 * 4. CompletableFuture.completeOnTimeout(value, long, TimeUnit): 超时则使用默认值完成
 * 5. CompletableFuture.delayedExecutor(long, TimeUnit): 创建延迟执行的线程池
 *
 * @author lingwh
 * @date 2026/08/06 14:07
 */
public class CompletableFutureTest {

    /**
     * 测试 CompletableFuture.completeAsync(Supplier): 使用 ForkJoinPool 异步完成任务
     */
    @Test
    public void testCompleteAsync() throws Exception {
        CompletableFuture<String> future = new CompletableFuture<>();
        // 使用默认的 ForkJoinPool 异步完成
        CompletableFuture<String> result = future.completeAsync(() -> {
            System.out.println("异步任务执行中..., 当前线程: " + Thread.currentThread().getName());
            return "异步任务结果";
        });
        System.out.println("completeAsync 结果: " + result.get());
    }

    /**
     * 测试 CompletableFuture.completeAsync(Supplier, Executor): 使用指定线程池异步完成
     */
    @Test
    public void testCompleteAsyncWithExecutor() throws Exception {
        CompletableFuture<String> future = new CompletableFuture<>();
        Executor executor = Executors.newSingleThreadExecutor();
        // 使用自定义线程池异步完成
        CompletableFuture<String> result = future.completeAsync(() -> {
            System.out.println("自定义线程池执行任务, 当前线程: " + Thread.currentThread().getName());
            return "自定义线程池异步结果";
        }, executor);
        System.out.println("completeAsync 自定义线程池结果: " + result.get());
    }

    /**
     * 测试 CompletableFuture.orTimeout(): 超时则抛出 TimeoutException
     */
    @Test(expected = java.util.concurrent.TimeoutException.class)
    public void testOrTimeout() throws Exception {
        CompletableFuture<String> future = new CompletableFuture<>();
        // 设置超时时间, 在指定时间内未完成则抛出 TimeoutException
        CompletableFuture<String> withTimeout = future.orTimeout(1, TimeUnit.SECONDS);
        // 模拟长时间任务, 故意不完成, 触发超时
        Thread.sleep(2000);
        // 这里不会执行到, 因为 orTimeout 已触发超时异常
        future.complete("完成");
        System.out.println("结果: " + withTimeout.get());
    }

    /**
     * 测试 CompletableFuture.completeOnTimeout(): 超时则使用默认值完成
     */
    @Test
    public void testCompleteOnTimeout() throws Exception {
        CompletableFuture<String> future = new CompletableFuture<>();
        // 设置超时时间, 超时后使用默认值完成, 而非抛出异常
        CompletableFuture<String> withDefault = future.completeOnTimeout("超时默认值", 1, TimeUnit.SECONDS);
        // 模拟长时间任务, 等待超时触发
        Thread.sleep(2000);
        // 超时后获取到的是默认值
        System.out.println("completeOnTimeout 结果: " + withDefault.get());
        // 此时再调用 complete 也不会生效, 因为已经由默认值完成了
        boolean completed = future.complete("延迟完成");
        System.out.println("再次 complete 是否成功: " + completed);
        System.out.println("最终结果仍然是默认值: " + withDefault.get());
    }

    /**
     * 测试 CompletableFuture.delayedExecutor(): 创建延迟执行的线程池
     */
    @Test
    public void testDelayedExecutor() throws Exception {
        // 创建一个延迟 2 秒执行的线程池
        Executor delayedExecutor = CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("延迟任务执行中..., 当前线程: " + Thread.currentThread().getName());
            return "延迟执行结果";
        }, delayedExecutor);
        // 验证任务被延迟执行
        long start = System.currentTimeMillis();
        String result = future.get();
        long duration = System.currentTimeMillis() - start;
        System.out.println("耗时: " + duration + "ms, 结果: " + result);
    }

    /**
     * 测试 delayedExecutor 与 completeAsync 组合使用
     */
    @Test
    public void testDelayedExecutorWithCompleteAsync() throws Exception {
        CompletableFuture<String> future = new CompletableFuture<>();
        // 创建延迟 1 秒的线程池, 并在 1 秒后异步完成
        Executor delayedExecutor = CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS);
        CompletableFuture<String> result = future.completeAsync(() -> "延迟异步完成", delayedExecutor);
        long start = System.currentTimeMillis();
        System.out.println("延迟异步结果: " + result.get() + ", 耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * 测试 completeOnTimeout 在真实场景中的应用: 服务调用超时兜底
     */
    @Test
    public void testCompleteOnTimeoutApplication() throws Exception {
        // 模拟远程服务调用, 正常情况下耗时 3 秒, 但超时设置为 1 秒
        CompletableFuture<String> serviceCall = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "远程服务返回结果";
        });
        // 设置超时兜底, 1 秒未返回则使用缓存数据
        CompletableFuture<String> withFallback = serviceCall.completeOnTimeout("缓存数据(服务超时)", 1, TimeUnit.SECONDS);
        long start = System.currentTimeMillis();
        String result = withFallback.get();
        System.out.println("服务调用结果: " + result + ", 耗时: " + (System.currentTimeMillis() - start) + "ms");
    }
}