package org.bluebridge.basic;

import org.junit.Test;

/**
 * @author lingwh
 * @desc
 * @date 2026/3/14 14:19
 */
public class IntegerTest {

    /**
     * 统计二进制位中1的个数
     */
    @Test
    public void bitCountTest() {
        int i = 8;
        System.out.println(Integer.bitCount(i));
    }
}
