package org.bluebridge.cas_01_helloworld;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

/**
 * 自定义AtomicInteger测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class MyAtomicIntegerTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2);
        System.out.println(updateAndGet(atomicInteger, x -> x * 2));
    }

    /**
     * 模拟AtomicInteger中的updateAndGet()
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
