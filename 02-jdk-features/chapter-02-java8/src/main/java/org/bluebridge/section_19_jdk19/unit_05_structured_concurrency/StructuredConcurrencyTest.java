package org.bluebridge.section_19_jdk19.unit_05_structured_concurrency;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

/**
 * JDK 19 结构化并发预览测试（JEP 428 - Structured Concurrency）
 *     注意：JDK 19 PREVIEW 特性，需要 --enable-preview
 *     StructuredTaskScope 在 JDK 19 中为预览特性，需要 --enable-preview 编译和运行
 *
 * @author lingwh
 * @date 2026/08/05 19:11
 */
public class StructuredConcurrencyTest {

    /**
     * 测试使用 StructuredTaskScope 创建基本结构化并发任务
     *     StructuredTaskScope 确保子任务的生命周期被限定在词法作用域内
     */
    @Test
    public void testBasicStructuredTaskScope_Preview() throws ExecutionException, InterruptedException {
        // 注意：以下代码需要 JDK 19 + --enable-preview 编译运行
        // 使用 try-with-resources 确保所有子任务在作用域结束时完成或取消
        try (var scope = new StructuredTaskScope<String>()) {
            // 在作用域内提交子任务
            Future<String> task1 = scope.fork(() -> {
                TimeUnit.MILLISECONDS.sleep(100);
                return "任务 1 完成";
            });
            Future<String> task2 = scope.fork(() -> {
                TimeUnit.MILLISECONDS.sleep(200);
                return "任务 2 完成";
            });

            // 等待所有子任务完成
            scope.join();

            // 获取子任务结果
            System.out.println(task1.resultNow());
            System.out.println(task2.resultNow());
        }
        System.out.println("所有子任务已完成，作用域已关闭");
    }

    /**
     * 测试使用 ShutdownOnFailure 策略 - 任一子任务失败则取消所有任务
     *     ShutdownOnFailure 是结构化并发的常见策略：
     *     当任意一个子任务抛出异常时，立即取消其他未完成的任务
     */
    @Test
    public void testShutdownOnFailure_Preview() throws Exception {
        // 注意：以下代码需要 JDK 19 + --enable-preview 编译运行
        // 使用 ShutdownOnFailure 策略：任一子任务失败则关闭作用域
        try (var scope = new StructuredTaskScope.ShutdownOnFailure<String>()) {
            // 提交多个子任务
            Future<String> task1 = scope.fork(() -> {
                TimeUnit.MILLISECONDS.sleep(100);
                return "用户数据加载完成";
            });
            Future<String> task2 = scope.fork(() -> {
                TimeUnit.MILLISECONDS.sleep(200);
                return "订单数据加载完成";
            });
            Future<String> task3 = scope.fork(() -> {
                TimeUnit.MILLISECONDS.sleep(150);
                return "商品数据加载完成";
            });

            // 等待所有子任务完成或任一失败
            scope.join();
            // 检查是否有任务失败，有则抛出异常
            scope.throwIfFailed();

            // 所有任务成功，获取结果
            System.out.println(task1.resultNow());
            System.out.println(task2.resultNow());
            System.out.println(task3.resultNow());
        }
        System.out.println("ShutdownOnFailure 策略演示完成，所有任务成功");
    }

    /**
     * 测试使用 ShutdownOnSuccess 策略 - 任一子任务成功则取消其他任务
     *     ShutdownOnSuccess 适用于多个子任务执行相同操作，
     *     只要其中一个成功返回结果，就立即取消其他任务
     */
    @Test
    public void testShutdownOnSuccess_Preview() throws Exception {
        // 注意：以下代码需要 JDK 19 + --enable-preview 编译运行
        // 使用 ShutdownOnSuccess 策略：任一子任务成功则关闭作用域
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            // 多个子任务执行相同操作，取最先成功的
            Future<String> task1 = scope.fork(() -> {
                TimeUnit.MILLISECONDS.sleep(300);
                return "服务 A 响应";
            });
            Future<String> task2 = scope.fork(() -> {
                TimeUnit.MILLISECONDS.sleep(100);
                return "服务 B 响应";
            });
            Future<String> task3 = scope.fork(() -> {
                TimeUnit.MILLISECONDS.sleep(200);
                return "服务 C 响应";
            });

            // 等待任一子任务成功
            scope.join();

            // 获取最先成功的结果
            String result = scope.result();
            System.out.println("最先成功的结果: " + result);
            System.out.println("任务 1 状态: " + (task1.isDone() ? "已完成" : "已取消"));
            System.out.println("任务 2 状态: " + (task2.isDone() ? "已完成" : "已取消"));
            System.out.println("任务 3 状态: " + (task3.isDone() ? "已完成" : "已取消"));
        }
        System.out.println("ShutdownOnSuccess 策略演示完成");
    }

    /**
     * 测试结构化并发 - 模拟聚合查询场景
     *     模拟从多个数据源并行加载数据并聚合结果
     */
    @Test
    public void testAggregateQuery_Preview() throws Exception {
        // 注意：以下代码需要 JDK 19 + --enable-preview 编译运行
        // 模拟聚合查询：同时从多个数据源加载数据
        try (var scope = new StructuredTaskScope.ShutdownOnFailure<String>()) {
            // 并行查询多个数据源
            Future<String> userInfo = scope.fork(() -> {
                TimeUnit.MILLISECONDS.sleep(150);
                return "用户: 张三, 年龄: 28";
            });
            Future<String> orderInfo = scope.fork(() -> {
                TimeUnit.MILLISECONDS.sleep(200);
                return "订单: #20240805, 金额: ¥299.00";
            });
            Future<String> productInfo = scope.fork(() -> {
                TimeUnit.MILLISECONDS.sleep(100);
                return "商品: 笔记本电脑 × 1";
            });

            // 等待所有查询完成
            scope.join();
            scope.throwIfFailed();

            // 聚合结果
            StringBuilder report = new StringBuilder();
            report.append("===== 聚合查询报告 =====\n");
            report.append(userInfo.resultNow()).append("\n");
            report.append(orderInfo.resultNow()).append("\n");
            report.append(productInfo.resultNow()).append("\n");
            report.append("=========================");

            System.out.println(report.toString());
        }
        System.out.println("聚合查询完成");
    }

    /**
     * 测试结构化并发 - 超时控制
     *     使用 join(long, TimeUnit) 设置超时时间
     */
    @Test
    public void testStructuredTaskScopeWithTimeout_Preview() throws Exception {
        // 注意：以下代码需要 JDK 19 + --enable-preview 编译运行
        // 使用 StructuredTaskScope 并设置超时
        try (var scope = new StructuredTaskScope.ShutdownOnFailure<String>()) {
            Future<String> task1 = scope.fork(() -> {
                // 模拟耗时操作
                TimeUnit.MILLISECONDS.sleep(500);
                return "耗时任务完成";
            });

            // 设置超时时间 300ms，短于任务执行时间
            try {
                scope.join(300, TimeUnit.MILLISECONDS);
                System.out.println("所有任务在超时前完成");
            } catch (TimeoutException e) {
                System.out.println("任务超时，未在指定时间内完成");
                // 超时后取消所有未完成的任务
                scope.shutdown();
            }

            System.out.println("任务 1 是否完成: " + task1.isDone());
            if (task1.isDone() && !task1.isCancelled()) {
                System.out.println("任务 1 结果: " + task1.resultNow());
            }
        }
        System.out.println("超时控制演示完成");
    }

    /**
     * 测试结构化并发 - 异常处理场景
     *     当一个子任务抛出异常时，ShutdownOnFailure 会取消其他任务
     */
    @Test
    public void testExceptionHandling_Preview() throws Exception {
        // 注意：以下代码需要 JDK 19 + --enable-preview 编译运行
        // 演示异常处理：一个任务失败会影响其他任务
        try (var scope = new StructuredTaskScope.ShutdownOnFailure<String>()) {
            Future<String> task1 = scope.fork(() -> {
                TimeUnit.MILLISECONDS.sleep(50);
                return "正常任务完成";
            });
            Future<String> task2 = scope.fork(() -> {
                TimeUnit.MILLISECONDS.sleep(100);
                // 模拟异常
                throw new RuntimeException("数据加载失败");
            });
            Future<String> task3 = scope.fork(() -> {
                TimeUnit.MILLISECONDS.sleep(150);
                return "这个任务可能被取消";
            });

            // 等待所有任务完成或任一失败
            scope.join();
            try {
                // 检查是否有任务失败
                scope.throwIfFailed();
            } catch (Exception e) {
                System.out.println("捕获到异常: " + e.getMessage());
            }

            System.out.println("任务 1 结果: " + task1.resultNow());
            System.out.println("任务 2 状态: " + (task2.isDone() ? "已完成(异常)" : "未完成"));
            System.out.println("任务 3 状态: " + (task3.isDone() ? "已完成" : "已取消"));
        }
        System.out.println("异常处理演示完成");
    }
}