﻿package org.bluebridge.section_09_jdk9.unit_04_string;

import org.junit.Test;

import java.util.stream.Collectors;

/**
 * Java9 String 相关方法测试
 *
 * 说明: chars() 和 codePoints() 是 CharSequence 接口中的默认方法(Java8 引入),
 * String 实现了 CharSequence 接口, 在 Java9 中同样可以使用, 配合 Stream 可以很方便地
 * 处理字符串中的每一个字符:
 * 1. chars(): 返回 IntStream, 流中的每个元素是字符串中字符的 int 值
 * 2. codePoints(): 返回 IntStream, 流中的每个元素是字符串的 Unicode 码点
 *    (支持增补字符, 可以正确处理 emoji 等需要 2 个 char 表示的字符)
 * 另外 Java9 在 JDK 内部引入了紧凑字符串(Compact Strings), 底层存储结构得到了优化,
 * 但对开发者是透明的, 无需修改任何代码即可获得内存上的收益
 *
 * @author lingwh
 * @date 2026/08/05 18:23
 */
public class StringMethodsTest {

    /**
     * 测试 chars(): 将字符串转换为字符流, 遍历每一个字符
     */
    @Test
    public void testChars() {
        String str = "hello";
        // chars() 返回 IntStream, 需要转为 char 再输出
        System.out.print("字符串 \"" + str + "\" 中的字符: ");
        str.chars().forEach(ch -> System.out.print((char) ch + " "));
        System.out.println();
        System.out.println("--------------------------------------");
        // 统计字符串中字母 'l' 出现的次数
        long count = str.chars().filter(ch -> ch == 'l').count();
        System.out.println("字符串 \"" + str + "\" 中字符 'l' 出现的次数: " + count);
        // 将每个字符收集为 String 列表
        System.out.println("chars() 收集结果: " + str.chars()
                .mapToObj(ch -> String.valueOf((char) ch))
                .collect(Collectors.toList()));
    }

    /**
     * 测试 codePoints(): 获取字符串的 Unicode 码点, 支持增补字符
     */
    @Test
    public void testCodePoints() {
        // 普通字符串
        String str = "hello";
        System.out.print("str.codePoints() 输出: ");
        str.codePoints().forEach(codePoint -> System.out.print(codePoint + " "));
        System.out.println();
        System.out.println("--------------------------------------");
        // 包含 emoji(增补字符)的字符串: emoji 需要 2 个 char 表示, 但只占 1 个码点
        String emojiStr = "hello\uD83D\uDE00";
        System.out.println("emoji 字符串的 char 数量: " + emojiStr.length());
        System.out.println("emoji 字符串的码点数量: " + emojiStr.codePointCount(0, emojiStr.length()));
        // codePoints() 可以正确处理增补字符, 每个 emoji 只输出 1 个码点
        System.out.print("codePoints() 输出: ");
        emojiStr.codePoints().forEach(codePoint -> System.out.print(codePoint + " "));
        System.out.println();
    }

    /**
     * 测试 codePoints() 与 chars() 在处理增补字符时的差异
     */
    @Test
    public void testCharsAndCodePointsDifference() {
        // 笑脸 emoji 由 2 个 char 组成(高位代理项 + 低位代理项)
        String emojiStr = "\uD83D\uDE00";
        System.out.print("chars() 输出(2 个 char 值): ");
        emojiStr.chars().forEach(ch -> System.out.print(ch + " "));
        System.out.println();
        System.out.print("codePoints() 输出(1 个码点): ");
        emojiStr.codePoints().forEach(codePoint -> System.out.print(codePoint + " "));
        System.out.println();
    }

    /**
     * 测试 chars() 在实际场景中的应用: 统计字符串中数字的个数并提取数字
     */
    @Test
    public void testCharsApplication() {
        String str = "abc123def456";
        // 统计字符串中数字字符的个数
        long digitCount = str.chars()
                .filter(Character::isDigit)
                .count();
        System.out.println("字符串 \"" + str + "\" 中数字的个数: " + digitCount);
        // 将字符串中的数字字符筛选并拼接起来
        String digits = str.chars()
                .filter(Character::isDigit)
                .mapToObj(ch -> String.valueOf((char) ch))
                .collect(Collectors.joining());
        System.out.println("提取出的数字: " + digits);
    }
}
