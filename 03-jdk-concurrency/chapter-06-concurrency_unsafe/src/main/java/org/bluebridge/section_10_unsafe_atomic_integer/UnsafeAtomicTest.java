package org.bluebridge.section_10_unsafe_atomic_integer;

/**
 * Unsafe 原子整数测试
 *
 * @author lingwh
 * @date 2026/4/21 13:45
 */
public class UnsafeAtomicTest {

    public static void main(String[] args) {
        // 赋初始值 10000，调用 demo 后正确的输出结果为 0
        AccountImpl account = new AccountImpl(10000);
        // 结果正确地输出 0
        account.demo();
    }
}
