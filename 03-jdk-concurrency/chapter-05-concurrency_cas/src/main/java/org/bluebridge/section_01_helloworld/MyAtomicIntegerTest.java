package org.bluebridge.section_01_helloworld;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

/**
 * 自定义 AtomicInteger 测试
 *
 * @author lingwh
 * @date 2026/4/21 10:30
 */
public class MyAtomicIntegerTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2);
        System.out.println(updateAndGet(atomicInteger, x -> x * 2));
    }

    /**
     * 模拟 AtomicInteger 中的 updateAndGet()
     *
     * @param i
     * @param operator
     * @return
     */
    public static int updateAndGet(AtomicInteger i, IntUnaryOperator operator) {
        while (true) {
            int prev = i.get();
            // 使用函数式接口封装更新之的逻辑
            int next = operator.applyAsInt(prev);
            if (i.compareAndSet(prev, next)) {
                return next;
            }
        }
    }
}
