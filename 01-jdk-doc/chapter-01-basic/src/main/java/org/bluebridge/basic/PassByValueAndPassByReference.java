package org.bluebridge.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 值传递和引用传递测试
 * @date 2025/5/3 16:58
 */
@Slf4j
public class PassByValueAndPassByReference {

    @Test
    public void testSwapNumber() {
        int a = 10, b = 20;
        swapNumber(a,b);
        // a和b的值没有改变的原因是java是值传递，在swapNumber()方法中，操作的是实参a和b的副本，而不是a和b本身
        log.info("a = {}, b = {}", a, b);
    }

    /**
     * 交换两个数
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
