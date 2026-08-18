package org.bluebridge.section_01_basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 原码、反码、补码
 *
 * @author lingwh
 * @date 2025/5/3 16:58
 */
@Slf4j
public class Code {

    @Test
    public void testCode() {
        log.info("--------");
        // 十进制数字5 -> 二进制数字
        log.info(toBinaryStringWithLeftPadding(5, 8));
        log.info(toBinaryStringWithLeftPadding(5, 8));
        log.info(toBinaryStringWithLeftPadding(1 << 7, 8));
        log.info("--------");

        int num = 5;
        log.info(toBinaryString(num, false));
        log.info(toBinaryString((1 << 7) | num, true));

        int i = 9;
        log.info("{}", 8 - i % 8);
        i = 4;
        log.info("{}", 8 - i % 8);
        i = 15;
        log.info("{}", 8 - i % 8);
    }

    /**
     * 使用二进制表示十进制数，并且左补0进行格式化
     *
     * @param num
     * @param debug
     * @return
     */
    private static String toBinaryString(int num, boolean debug) {
        List<Integer> bits = new ArrayList<>();
        for (int i = 7; i >= 0; i--) {
            if (debug) {
                log.info(toBinaryStringWithLeftPadding((num >> i), 8));
                log.info(toBinaryStringWithLeftPadding(1, 8));
                log.info(toBinaryStringWithLeftPadding((num >> i) & 1, 8));
                log.info("--------");
            }
            bits.add((num >> i) & 1);
        }
        return bits.stream().map(String::valueOf).collect(Collectors.joining(""));
    }

    /**
     * 使用二进制表示十进制数，并且左补0进行格式化 - 方式一：String.format()实现
     *
     * @param decimal
     * @param length
     * @return
     */
    private static String toBinaryStringWithLeftPadding(int decimal, int length) {
        return String.format("%" + length + "s", Integer.toBinaryString(decimal)).replace(' ', '0');
    }
}
