package org.bluebridge.section_01_basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 值传递和引用传递测试
 *
 * @author lingwh
 * @date 2025/5/3 16:58
 */
@Slf4j
public class PassByValueAndPassByReference {

    @Test
    public void testSwapNumber() {
        int a = 10, b = 20;
        swapNumber(a, b);
        // a 和 b 的值没有改变的原因是 java 是值传递，在 swapNumber() 方法中，操作的是实参 a 和 b 的副本，而不是 a 和 b 本身
        log.info("a = {}, b = {}", a, b);
    }

    /**
     * 交换两个数
     *
     * @param a
     * @param b
     */
    private static void swapNumber(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        log.info("a = {}, b = {}", a, b);
    }
}
