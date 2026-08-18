package org.bluebridge.section_11_jdk11_lts.unit_01_string;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * JDK 11 String 新增方法测试
 *
 * 1. isBlank(): 判断字符串是否为空或只包含空白字符
 * 2. lines(): 将字符串按行拆分, 返回 Stream<String>
 * 3. strip(): 去除字符串首尾的空白字符(基于 Unicode, 比 trim() 更强大)
 * 4. stripLeading(): 只去除字符串首部的空白字符
 * 5. stripTrailing(): 只去除字符串尾部的空白字符
 * 6. repeat(int): 将字符串重复拼接指定次数
 *
 * @author lingwh
 * @date 2026/08/06 09:15
 */
public class StringTest {

    /**
     * 测试 isBlank() 方法: 判断字符串是否为空或只包含空白字符
     */
    @Test
    public void testIsBlank() {
        // 空字符串
        System.out.println("'': " + "".isBlank());
        // 只包含空格
        System.out.println("'   ': " + "   ".isBlank());
        // 只包含制表符
        System.out.println("'\t\t': " + "\t\t".isBlank());
        // 只包含换行符
        System.out.println("'\n': " + "\n".isBlank());
        // 包含非空白字符
        System.out.println("'hello': " + "hello".isBlank());
    }

    /**
     * 测试 lines() 方法: 将字符串按行拆分成 Stream<String>
     */
    @Test
    public void testLines() {
        String multilineString = "Java\nPython\r\nGo\rC++";
        System.out.println("按行拆分的每一行:");
        // 使用 lines() 将多行文本拆分为多行
        Stream<String> lines = multilineString.lines();
        lines.forEach(System.out::println);
        System.out.println("--------------------------------------");
        // 统计字符串的行数
        System.out.println("行数: " + "abc\ncde\nefg".lines().count());
    }

    /**
     * 测试 strip() 方法: 去除字符串首尾的空白字符
     */
    @Test
    public void testStrip() {
        String str = "   hello world   ";
        // strip() 去除首尾空白字符
        System.out.println("strip(): '" + str.strip() + "'");
        // 对比 trim(): trim() 只能去除码点小于等于 U+0020 的空白字符
        System.out.println("trim(): '" + str.trim() + "'");
        System.out.println("--------------------------------------");
        // 使用全角空格(Unicode 空白字符)时 strip() 与 trim() 的差异
        String fullWidthBlank = "\u3000hello\u3000";
        System.out.println("strip(): '" + fullWidthBlank.strip() + "'");
        System.out.println("trim(): '" + fullWidthBlank.trim() + "'");
    }

    /**
     * 测试 stripLeading() 方法: 只去除字符串首部的空白字符
     */
    @Test
    public void testStripLeading() {
        String str = "   hello world   ";
        System.out.println("stripLeading(): '" + str.stripLeading() + "'");
    }

    /**
     * 测试 stripTrailing() 方法: 只去除字符串尾部的空白字符
     */
    @Test
    public void testStripTrailing() {
        String str = "   hello world   ";
        System.out.println("stripTrailing(): '" + str.stripTrailing() + "'");
    }

    /**
     * 测试 repeat(int) 方法: 将字符串重复拼接指定次数
     */
    @Test
    public void testRepeat() {
        // 重复 3 次
        System.out.println("'abc'.repeat(3): " + "abc".repeat(3));
        // 重复 0 次, 返回空串
        System.out.println("'abc'.repeat(0): '" + "abc".repeat(0) + "'");
        // 动态生成分隔线
        System.out.println("-".repeat(30));
        // 生成固定长度的字符串
        System.out.println("0".repeat(6));
    }
}
