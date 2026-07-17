package org.bluebridge.java11;

import org.junit.Test;

/**
 * Java11 String 测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class StringTest {

    /**
     * java11中String的新增方法
     */
    @Test
    public void testString() {
        // isBlank()
        System.out.println("    ".isBlank());
        System.out.println("\t\t".isBlank());

        // 去首尾空格
        System.out.println("-----" + "  ".strip() + "-----");

        // 去尾部空格
        System.out.println("-----    ".stripTrailing());

        // 去头部空格
        System.out.println("    -----".stripLeading());

        // 复制字符串
        System.out.println("abc".repeat(5));

        // 统计字符串行数
        System.out.println("abc\ncde\nefg".lines().count());
    }
}
