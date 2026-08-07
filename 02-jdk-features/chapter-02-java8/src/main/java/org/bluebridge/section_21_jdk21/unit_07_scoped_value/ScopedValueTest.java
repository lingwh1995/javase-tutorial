package org.bluebridge.section_21_jdk21.unit_07_scoped_value;

import org.junit.Test;

/**
 * JDK 21 Scoped Value 测试(PREVIEW 预览特性)
 *
 * Scoped Value(JEP 429, 第二次预览) 是 JDK 21 的 PREVIEW 预览特性,
 * 编译和运行都需要 --enable-preview 参数。
 *
 * Scoped Value 是一种在线程内部和线程之间共享不可变数据的新机制,
 * 是 ThreadLocal 的替代方案, 具有以下优势:
 *   1. 不可变性: ScopedValue 一旦绑定就不能修改, 保证了数据安全
 *   2. 继承性: 子线程自动继承父线程的 ScopedValue 绑定
 *   3. 生命周期: 绑定值只在 Runnable/Callable 执行期间有效
 *   4. 性能: 比 ThreadLocal 更高效, 特别是在虚拟线程场景下
 *
 * 演化历程:
 *   - JDK 20: JEP 429 第一次预览
 *   - JDK 21: JEP 429 第二次预览
 *   - JDK 22: JEP 464 第三次预览
 *   - 待定: 最终转正
 *
 * @author lingwh
 * @date 2026/08/06 18:18
 */
public class ScopedValueTest {

    // 创建 ScopedValue 实例
    private static final ScopedValue<String> SCOPED_VALUE = ScopedValue.newInstance();
    private static final ScopedValue<Integer> SCOPED_USER_ID = ScopedValue.newInstance();

    /**
     * 测试 ScopedValue 的基本使用(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * 使用 ScopedValue.where().run() 绑定值并在指定作用域内执行
     */
    @Test
    public void testBasicScopedValue_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 绑定值并在 run 方法的作用域内使用
        ScopedValue.where(SCOPED_VALUE, "Hello, ScopedValue!")
                .run(() -> {
                    // 在作用域内获取绑定的值
                    String value = SCOPED_VALUE.get();
                    System.out.println("获取 ScopedValue: " + value);
                });
        // 离开 run 的作用域后, SCOPED_VALUE 不再有值
        System.out.println("作用域外, ScopedValue.isBound() = " + SCOPED_VALUE.isBound());
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 ScopedValue 的嵌套绑定(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * ScopedValue 支持嵌套绑定, 内部绑定会覆盖外部绑定
     */
    @Test
    public void testNestedScopedValue_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 外部绑定
        ScopedValue.where(SCOPED_VALUE, "外部值")
                .run(() -> {
                    System.out.println("外部作用域: " + SCOPED_VALUE.get());

                    // 内部绑定, 覆盖外部值
                    ScopedValue.where(SCOPED_VALUE, "内部值")
                            .run(() -> {
                                System.out.println("内部作用域: " + SCOPED_VALUE.get());
                            });

                    // 回到外部作用域, 恢复外部值
                    System.out.println("回到外部作用域: " + SCOPED_VALUE.get());
                });
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 ScopedValue 在虚拟线程中传递(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * ScopedValue 可以在虚拟线程中传递, 子线程自动继承父线程的绑定
     */
    @Test
    public void testScopedValueWithVirtualThread_Preview() throws Exception {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 在虚拟线程中传递 ScopedValue
        ScopedValue.where(SCOPED_VALUE, "虚拟线程数据")
                .run(() -> {
                    System.out.println("主线程: " + SCOPED_VALUE.get());

                    // 创建虚拟线程, 继承 ScopedValue
                    Thread vt = Thread.startVirtualThread(() -> {
                        // 虚拟线程自动继承 ScopedValue
                        System.out.println("  虚拟线程: " + SCOPED_VALUE.get());
                    });
                    try {
                        vt.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 ScopedValue 的 call 方法返回值(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * 除了 run() 方法, ScopedValue.where().call() 可以返回结果
     */
    @Test
    public void testScopedValueCall_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 使用 call 方法获取返回值
        String result = ScopedValue.where(SCOPED_VALUE, "call 返回值")
                .call(() -> {
                    String value = SCOPED_VALUE.get();
                    return "处理结果: " + value;
                });
        System.out.println("call 返回结果: " + result);
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 ScopedValue 的不可变性(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * ScopedValue 一旦绑定就不能修改, 只能通过嵌套绑定创建新作用域
     */
    @Test
    public void testScopedValueImmutability_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // ScopedValue 是不可变的, 不能重新赋值
        ScopedValue.where(SCOPED_VALUE, "初始值")
                .run(() -> {
                    System.out.println("当前值: " + SCOPED_VALUE.get());
                    // 注意: ScopedValue 不能像 ThreadLocal 那样 set 修改
                    // 只能通过新的 where().run() 创建新的绑定
                });

        // 创建新的绑定
        ScopedValue.where(SCOPED_VALUE, "新值")
                .run(() -> {
                    System.out.println("新绑定值: " + SCOPED_VALUE.get());
                });
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 ScopedValue 的多个绑定(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * ScopedValue.where() 支持链式调用, 同时绑定多个值
     */
    @Test
    public void testMultipleScopedValues_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 同时绑定多个 ScopedValue
        ScopedValue.where(SCOPED_VALUE, "用户数据")
                .where(SCOPED_USER_ID, 1001)
                .run(() -> {
                    System.out.println("用户数据: " + SCOPED_VALUE.get());
                    System.out.println("用户 ID: " + SCOPED_USER_ID.get());
                });
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 ScopedValue 的 isBound 和 orElse 方法(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * isBound() 检查是否已绑定, orElse() 提供默认值
     */
    @Test
    public void testScopedValueBoundCheck_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 未绑定时检查
        System.out.println("未绑定时 isBound: " + SCOPED_VALUE.isBound());

        // 使用 orElse 提供默认值
        String defaultValue = SCOPED_VALUE.orElse("默认值");
        System.out.println("未绑定时 orElse: " + defaultValue);

        // 绑定时使用
        ScopedValue.where(SCOPED_VALUE, "实际值")
                .run(() -> {
                    System.out.println("绑定时 isBound: " + SCOPED_VALUE.isBound());
                    System.out.println("绑定时 orElse: " + SCOPED_VALUE.orElse("不会使用默认值"));
                    System.out.println("绑定时 get: " + SCOPED_VALUE.get());
                });
        System.out.println("--------------------------------------");
    }
}
