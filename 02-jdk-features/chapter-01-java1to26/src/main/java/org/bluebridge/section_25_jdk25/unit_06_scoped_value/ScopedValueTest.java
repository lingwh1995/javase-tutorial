package org.bluebridge.section_25_jdk25.unit_06_scoped_value;

import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * JDK 25 ScopedValue 作用域值测试(STANDARD 正式特性)
 *
 * Scoped Values(JEP 506) 是 JDK 25 转正的 STANDARD 正式特性, 提供了一种在线程内
 * 传递不可变上下文数据的安全机制, 常用于取代 ThreadLocal 传递请求上下文(用户身份、
 * 事务 ID、Trace ID 等)。作用域值只读且不会在线程之间意外泄漏。
 *
 * 核心 API:
 *   1. ScopedValue.newInstance(): 创建作用域值句柄
 *   2. ScopedValue.where(KEY, value).run(...)/.call(...): 绑定作用域值并运行代码
 *   3. KEY.get(): 在绑定范围内获取值
 *   4. KEY.isBound(): 判断当前线程是否已绑定该作用域值
 *   5. 嵌套绑定: 内层绑定会临时覆盖外层绑定
 *
 * 演化历程: ScopedValue JDK 21(JEP 446, 1st PREVIEW) → JDK 22(JEP 464, 2nd PREVIEW) → JDK 23(JEP 481, 3rd PREVIEW) → JDK 24(JEP 487, 4th PREVIEW) → JDK 25(JEP 506, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/18 09:10
 */
public class ScopedValueTest {

    /**
     * 声明一个作用域值句柄, 用于传递当前用户
     */
    private static final ScopedValue<String> CURRENT_USER = ScopedValue.newInstance();

    /**
     * 测试 ScopedValue 基本绑定与获取(STANDARD)
     * 在 where().run() 范围内可以获取值, 超出范围后无法获取
     */
    @Test
    public void testBasicScopedValue() {
        // ===== 旧版实现方式(JDK 25 之前): 使用 ThreadLocal 传递上下文, 需要手动 remove 防止泄漏 =====
        // ThreadLocal<String> user = new ThreadLocal<>();
        // user.set("张三");
        // try { ... } finally { user.remove(); }
        // ===== 新版实现方式(JDK 25 起): ScopedValue 绑定范围明确, 无需手动清理 =====
        System.out.println("绑定前是否已绑定: " + CURRENT_USER.isBound());
        // 绑定 CURRENT_USER = "张三" 并运行代码
        ScopedValue.where(CURRENT_USER, "张三").run(() -> {
            System.out.println("ScopedValue 基本用法测试:");
            System.out.println("  绑定后是否已绑定: " + CURRENT_USER.isBound());
            System.out.println("  获取当前用户: " + CURRENT_USER.get());
        });
        // 离开绑定范围后, 恢复为未绑定状态
        System.out.println("  离开范围后是否已绑定: " + CURRENT_USER.isBound());
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 ScopedValue 嵌套绑定(STANDARD)
     * 内层绑定会临时覆盖外层绑定, 退出内层后恢复外层值
     */
    @Test
    public void testNestedScopedValue() {
        ScopedValue.where(CURRENT_USER, "外层用户").run(() -> {
            System.out.println("外层绑定用户: " + CURRENT_USER.get());
            // 内层嵌套绑定, 临时覆盖
            ScopedValue.where(CURRENT_USER, "内层用户").run(() -> {
                System.out.println("内层嵌套绑定用户: " + CURRENT_USER.get());
            });
            // 退出内层后恢复外层值
            System.out.println("退出内层后用户: " + CURRENT_USER.get());
        });
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 ScopedValue 在 lambda/方法调用链中传递(STANDARD)
     * 作用域值可以穿透多层方法调用, 无需在参数中显式传递
     */
    @Test
    public void testScopedValueAcrossMethods() {
        ScopedValue.where(CURRENT_USER, "管理员").run(() -> {
            // 在深层方法调用中直接获取
            handleRequest();
        });
        System.out.println("--- 分割线 ---");
    }

    /**
     * 模拟处理请求的方法, 通过作用域值获取当前用户
     */
    private void handleRequest() {
        // 模拟业务处理中的若干层调用
        serviceLayer();
    }

    private void serviceLayer() {
        // 在任意嵌套层级都可以获取作用域值
        System.out.println("serviceLayer 获取当前用户: " + CURRENT_USER.get());
        daoLayer();
    }

    private void daoLayer() {
        System.out.println("daoLayer 获取当前用户: " + CURRENT_USER.get());
    }

    /**
     * 测试未绑定时调用 get() 抛出异常(STANDARD)
     * 在未绑定的范围内调用 get() 会抛出 NoSuchElementException
     */
    @Test
    public void testUnboundGetThrows() {
        System.out.println("未绑定 get() 异常测试:");
        try {
            // 未绑定范围内调用 get(), 应抛出异常
            String value = CURRENT_USER.get();
            System.out.println("  不应执行到这里: " + value);
        } catch (NoSuchElementException e) {
            System.out.println("  未绑定调用 get() 抛出: NoSuchElementException");
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 ScopedValue.call() 返回计算结果(STANDARD)
     * 与 run() 类似, call() 可以返回一个计算结果
     */
    @Test
    public void testScopedValueCall() {
        // call() 返回绑定范围内的计算结果
        String result = ScopedValue.where(CURRENT_USER, "李四").call(() -> {
            String user = CURRENT_USER.get();
            return "当前用户: " + user;
        });
        System.out.println("ScopedValue.call() 返回值: " + result);
        System.out.println("--- 分割线 ---");
    }
}
