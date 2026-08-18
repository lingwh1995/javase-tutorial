package org.bluebridge.section_11_jdk11_lts.unit_01_string;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * JDK 11 String 新方法测试
 *
 * @author lingwh
 * @date 2026/08/06 09:15
 */
public class StringTest {

    /**
     * 测试 String.isBlank() 方法
     * 判断字符串是否为空白（空字符串或仅包含空白字符）
     */
    @Test
    public void testIsBlank() {
        // 空白字符串
        String str1 = "";
        System.out.println("空字符串 isBlank: " + str1.isBlank());

        // 仅包含空格
        String str2 = "   ";
        System.out.println("空格字符串 isBlank: " + str2.isBlank());

        // 包含制表符
        String str3 = "\t";
        System.out.println("制表符字符串 isBlank: " + str3.isBlank());

        // 包含换行符
        String str4 = "\n";
        System.out.println("换行符字符串 isBlank: " + str4.isBlank());

        // 非空白字符串
        String str5 = "hello";
        System.out.println("非空白字符串 isBlank: " + str5.isBlank());
    }

    /**
     * 测试 String.lines() 方法
     * 将字符串按行分割，返回 Stream<String>
     */
    @Test
    public void testLines() {
        String multiline = "第一行\n第二行\n第三行";
        Stream<String> lines = multiline.lines();
        lines.forEach(System.out::println);
    }

    /**
     * 测试 String.strip() 方法
     * 去除字符串首尾的空白字符（支持 Unicode 空白，比 trim() 更强大）
     */
    @Test
    public void testStrip() {
        // 前后有空格
        String str1 = "  hello world  ";
        System.out.println("strip 前: '" + str1 + "'");
        System.out.println("strip 后: '" + str1.strip() + "'");

        // 包含 Unicode 空白字符
        String str2 = "\u2000hello\u2000";
        System.out.println("strip 前: '" + str2 + "'");
        System.out.println("strip 后: '" + str2.strip() + "'");

        // 对比 trim() 与 strip() 处理 Unicode 空白
        System.out.println("trim 结果: '" + str2.trim() + "'");
        System.out.println("strip 结果: '" + str2.strip() + "'");
    }

    /**
     * 测试 String.repeat(int) 方法
     * 将字符串重复指定次数后拼接返回
     */
    @Test
    public void testRepeat() {
        String str = "Hello ";
        System.out.println("重复 0 次: '" + str.repeat(0) + "'");
        System.out.println("重复 1 次: '" + str.repeat(1) + "'");
        System.out.println("重复 3 次: '" + str.repeat(3) + "'");

        // 实用场景：生成分隔线
        String separator = "-".repeat(50);
        System.out.println("分隔线: " + separator);
    }
}