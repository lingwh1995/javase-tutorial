package org.bluebridge.section_21_jdk21.unit_08_structured_concurrency;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.ShutdownOnFailure;
import java.util.concurrent.StructuredTaskScope.ShutdownOnSuccess;
import java.util.concurrent.Future;

/**
 * JDK 21 结构化并发测试(PREVIEW 预览特性)
 *
 * 结构化并发(Structured Concurrency, JEP 437, 第三次预览) 是 JDK 21 的
 * PREVIEW 预览特性, 编译和运行都需要 --enable-preview 参数。
 *
 * 结构化并发通过 StructuredTaskScope 将并发任务组织为结构化的工作单元,
 * 确保子任务的生命周期与父任务绑定, 简化错误处理和取消操作。
 *
 * 核心概念:
 *   1. StructuredTaskScope: 结构化任务作用域, 管理一组并发子任务
 *   2. ShutdownOnFailure: 策略, 任一子任务失败则关闭整个作用域
 *   3. ShutdownOnSuccess: 策略, 任一子任务成功则关闭整个作用域
 *   4. fork(): 提交子任务, 返回 Future
 *   5. join(): 等待所有子任务完成
 *
 * 演化历程:
 *   - JDK 19: JEP 428 第一次预览
 *   - JDK 20: JEP 437 第二次预览
 *   - JDK 21: JEP 437 第三次预览
 *   - JDK 22: JEP 462 转正(最终确定的 API)
 *
 * @author lingwh
 * @date 2026/08/06 18:18
 */
public class StructuredConcurrencyTest {

    /**
     * 测试 StructuredTaskScope 的基本使用(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * 使用 try-with-resources 管理 StructuredTaskScope 生命周期
     * fork() 提交子任务, join() 等待所有子任务完成
     */
    @Test
    public void testBasicStructuredTaskScope_Preview() throws Exception {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 使用 try-with-resources 自动关闭 StructuredTaskScope
        try (var scope = new StructuredTaskScope<String>()) {
            // 提交子任务
            Future<String> task1 = scope.fork(() -> {
                Thread.sleep(50);
                return "任务1 结果";
            });
            Future<String> task2 = scope.fork(() -> {
                Thread.sleep(100);
                return "任务2 结果";
            });
            Future<String> task3 = scope.fork(() -> {
                Thread.sleep(30);
                return "任务3 结果";
            });

            // 等待所有子任务完成
            scope.join();

            // 获取结果
            System.out.println("task1: " + task1.resultNow());
            System.out.println("task2: " + task2.resultNow());
            System.out.println("task3: " + task3.resultNow());
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 ShutdownOnFailure 策略(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * ShutdownOnFailure: 任一子任务失败时立即关闭整个作用域,
     * 适用于"全部成功才有意义"的场景
     */
    @Test
    public void testShutdownOnFailure_Preview() throws Exception {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // ShutdownOnFailure 策略, 任一任务失败则关闭所有任务
        try (var scope = new StructuredTaskScope.ShutdownOnFailure<String>()) {
            Future<String> task1 = scope.fork(() -> {
                Thread.sleep(50);
                return "任务1 成功";
            });
            Future<String> task2 = scope.fork(() -> {
                Thread.sleep(100);
                return "任务2 成功";
            });

            // 等待所有子任务完成或任一失败
            scope.join();
            // 检查是否有任务失败, 如果有则抛出异常
            scope.throwIfFailed();

            System.out.println("所有任务都成功完成:");
            System.out.println("  task1: " + task1.resultNow());
            System.out.println("  task2: " + task2.resultNow());
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 ShutdownOnFailure 处理失败情况(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * 当某个子任务抛出异常时, ShutdownOnFailure 会关闭作用域
     */
    @Test
    public void testShutdownOnFailureWithException_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        try (var scope = new StructuredTaskScope.ShutdownOnFailure<String>()) {
            Future<String> task1 = scope.fork(() -> {
                Thread.sleep(30);
                return "任务1 成功";
            });
            Future<String> task2 = scope.fork(() -> {
                Thread.sleep(50);
                throw new RuntimeException("任务2 执行失败");
            });

            // 等待子任务完成
            scope.join();
            // 检查异常, 会抛出 ExecutionException
            try {
                scope.throwIfFailed();
            } catch (ExecutionException e) {
                System.out.println("捕获到任务失败异常: " + e.getCause().getMessage());
            }

            System.out.println("task1 状态: " + (task1.isDone() ? "已完成" : "未完成"));
            System.out.println("task2 状态: " + (task2.isDone() ? "已完成" : "未完成"));
            if (task1.isDone() && !task1.isCancelled()) {
                System.out.println("task1 结果: " + task1.resultNow());
            }
        } catch (Exception e) {
            System.out.println("作用域异常: " + e.getMessage());
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 ShutdownOnSuccess 策略(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * ShutdownOnSuccess: 任一子任务成功时立即关闭整个作用域,
     * 适用于"任意一个成功即可"的场景
     */
    @Test
    public void testShutdownOnSuccess_Preview() throws Exception {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // ShutdownOnSuccess 策略, 任一任务成功则关闭其他任务
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            Future<String> task1 = scope.fork(() -> {
                Thread.sleep(100);
                return "任务1 结果(较慢)";
            });
            Future<String> task2 = scope.fork(() -> {
                Thread.sleep(30);
                return "任务2 结果(快速)";
            });
            Future<String> task3 = scope.fork(() -> {
                Thread.sleep(200);
                return "任务3 结果(最慢)";
            });

            // 等待任一子任务成功
            scope.join();
            // 获取第一个成功的结果
            String result = scope.result();
            System.out.println("第一个成功的结果: " + result);

            System.out.println("task1 状态: " + (task1.isDone() ? "已完成" : "可能已取消"));
            System.out.println("task2 状态: " + (task2.isDone() ? "已完成" : "可能已取消"));
            System.out.println("task3 状态: " + (task3.isDone() ? "已完成" : "可能已取消"));
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试结构化并发与虚拟线程结合(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * 结构化并发天然支持虚拟线程, 每个子任务可以运行在虚拟线程中
     */
    @Test
    public void testStructuredConcurrencyWithVirtualThread_Preview() throws Exception {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 结合虚拟线程使用结构化并发
        try (var scope = new StructuredTaskScope.ShutdownOnFailure<String>()) {
            // 提交多个模拟查询任务
            Future<String> userInfo = scope.fork(() -> {
                System.out.println("  查询用户信息: " + Thread.currentThread().getName());
                Thread.sleep(80);
                return "用户: 张三";
            });
            Future<String> orderInfo = scope.fork(() -> {
                System.out.println("  查询订单信息: " + Thread.currentThread().getName());
                Thread.sleep(50);
                return "订单: 20240806001";
            });
            Future<String> productInfo = scope.fork(() -> {
                System.out.println("  查询商品信息: " + Thread.currentThread().getName());
                Thread.sleep(30);
                return "商品: Java 教程";
            });

            // 等待所有查询完成
            scope.join();
            scope.throwIfFailed();

            // 汇总结果
            System.out.println("===== 查询汇总 =====");
            System.out.println(userInfo.resultNow());
            System.out.println(orderInfo.resultNow());
            System.out.println(productInfo.resultNow());
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 StructuredTaskScope 的异常传播(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * 异常会通过 Future 传播, join 后可以通过 Future.exceptionNow() 获取
     */
    @Test
    public void testExceptionHandling_Preview() throws Exception {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        try (var scope = new StructuredTaskScope<String>()) {
            Future<String> successTask = scope.fork(() -> {
                return "成功的结果";
            });
            Future<String> failTask = scope.fork(() -> {
                throw new IllegalArgumentException("无效参数异常");
            });

            // 等待所有任务完成
            scope.join();

            // 获取成功任务的结果
            System.out.println("成功任务: " + successTask.resultNow());

            // 获取失败任务的异常
            try {
                failTask.resultNow();
            } catch (IllegalArgumentException e) {
                System.out.println("失败任务异常: " + e.getMessage());
            }
        }
        System.out.println("--------------------------------------");
    }
}
