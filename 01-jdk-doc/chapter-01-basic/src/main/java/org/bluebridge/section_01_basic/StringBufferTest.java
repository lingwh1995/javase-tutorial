package org.bluebridge.section_01_basic;

import org.junit.Test;

/**
 * StringBuffer 测试
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class StringBufferTest {

    /**
     * 测试 StringBuffer 初始化容量和不初始化容量以及直接使用+=拼接字符串 性能差别： 不初始化容量： 161 ms 初始化容量： 93 ms 使用+=直接拼接：无限制长的时间
     */
    @Test
    public void appTest() {
        long begin1 = System.currentTimeMillis();
        StringBuffer stringBuffer1 = new StringBuffer();
        for (int i = 0; i < 1000000; i++) {
            String temp = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
            stringBuffer1.append(i + temp);
        }
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - begin1);

        long begin2 = System.currentTimeMillis();
        StringBuffer stringBuffer2 = new StringBuffer(20000);
        for (int i = 0; i < 1000000; i++) {
            String temp = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
            stringBuffer2.append(i + temp);
        }
        long end2 = System.currentTimeMillis();
        System.out.println(end2 - begin2);

        long begin3 = System.currentTimeMillis();
        String test = "";
        for (int i = 0; i < 10000; i++) {
            String temp = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
            test += temp;
        }
        long end3 = System.currentTimeMillis();
        System.out.println(end3 - begin3);
    }
}
