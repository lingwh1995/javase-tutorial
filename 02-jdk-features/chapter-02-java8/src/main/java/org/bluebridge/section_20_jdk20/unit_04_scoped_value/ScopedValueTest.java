package org.bluebridge.section_20_jdk20.unit_04_scoped_value;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * JDK 20 作用域值测试（PREVIEW 特性）
 * @see JEP 429: Scoped Values (Incubator)
 * @author lingwh
 * @date 2026/08/05 19:11
 */
public class ScopedValueTest {

    /**
     * 定义一个 ScopedValue 用于测试
     */
    private static final ScopedValue<String> SCOPED_USER = ScopedValue.newInstance();

    /**
     * 定义一个 ScopedValue 用于存储请求 ID
     */
    private static final ScopedValue<Long> SCOPED_REQUEST_ID = ScopedValue.newInstance();

    /**
     * 测试 ScopedValue 的基本绑定和获取
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testScopedValueBasic_Preview() throws Exception {
        // 使用 ScopedValue.where() 绑定值，在 run 方法的作用域内可访问
        ScopedValue.where(SCOPED_USER, "Alice")
                .run(() -> {
                    String user = SCOPED_USER.get();
                    System.out.println("testScopedValueBasic_Preview: 当前用户 = " + user);
                    // 在嵌套作用域中可以重新绑定
                    ScopedValue.where(SCOPED_USER, "Bob")
                            .run(() -> {
                                String nestedUser = SCOPED_USER.get();
                                System.out.println("testScopedValueBasic_Preview: 嵌套作用域用户 = " + nestedUser);
                            });
                    // 退出嵌套作用域后恢复为原来的值
                    String restoredUser = SCOPED_USER.get();
                    System.out.println("testScopedValueBasic_Preview: 恢复后的用户 = " + restoredUser);
                });
    }

    /**
     * 测试 ScopedValue 在虚拟线程中的传递
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testScopedValueWithVirtualThread_Preview() throws Exception {
        Callable<String> task = () -> {
            String user = SCOPED_USER.get();
            long requestId = SCOPED_REQUEST_ID.get();
            return "testScopedValueWithVirtualThread_Preview: 用户=" + user + ", 请求ID=" + requestId
                    + ", 线程=" + Thread.currentThread().getName();
        };

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<String> future = ScopedValue.where(SCOPED_USER, "Charlie")
                    .where(SCOPED_REQUEST_ID, 1001L)
                    .call(() -> executor.submit(task).get());

            System.out.println(future.get());
        }
    }

    /**
     * 测试 ScopedValue 的不可变性和作用域隔离
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testScopedValueIsolation_Preview() throws Exception {
        Runnable task1 = () -> {
            String user = SCOPED_USER.get();
            System.out.println("testScopedValueIsolation_Preview: 任务1中用户 = " + user);
        };

        Runnable task2 = () -> {
            String user = SCOPED_USER.get();
            System.out.println("testScopedValueIsolation_Preview: 任务2中用户 = " + user);
        };

        // 不同的作用域绑定不同的值，互不干扰
        ScopedValue.where(SCOPED_USER, "Dave").run(task1);
        ScopedValue.where(SCOPED_USER, "Eve").run(task2);
    }

    /**
     * 测试 ScopedValue 的 call 方法（带返回值）
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testScopedValueCall_Preview() throws Exception {
        String result = ScopedValue.where(SCOPED_USER, "Frank")
                .where(SCOPED_REQUEST_ID, 2026L)
                .call(() -> {
                    String user = SCOPED_USER.get();
                    Long reqId = SCOPED_REQUEST_ID.get();
                    return "用户: " + user + ", 请求ID: " + reqId;
                });
        System.out.println("testScopedValueCall_Preview: 返回值 = " + result);
    }
}