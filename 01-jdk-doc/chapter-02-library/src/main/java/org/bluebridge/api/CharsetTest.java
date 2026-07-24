package org.bluebridge.api;

import org.junit.Test;

import java.nio.charset.Charset;

/**
 * 字符集测试
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class CharsetTest {

    /**
     * 获取 JVM 默认字符集
     */
    @Test
    public void testDefaultCharset() {
        // 获取默认的字符集
        Charset defaultCharset = Charset.defaultCharset();

        // 打印默认字符集的名称
        System.out.println("Default charset: " + defaultCharset.name());

        // 打印默认字符集的别名
        System.out.println("Aliases: " + defaultCharset.aliases());
    }
}
