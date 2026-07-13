package org.bluebridge.lang3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.CharUtils;
import org.junit.Test;

/**
 * 字符工具类测试类
 *
 * @author lingwh
 * @date 2025/11/7 11:49
 */
@Slf4j
public class CharUtilsTest {

    @Test
    public void testCharUtils() {
        char alpha = 'A';
        char symbol = '\n';
        char number = '5';

        // 测试字符转换
        log.info("字符 {} 转换结果：{}", alpha, CharUtils.toString(alpha));

        // 判断是否为ASCII字符/可打印ASCII字符
        log.info("字符 {} 是否为ASCII字符：{}", alpha, CharUtils.isAscii(alpha));
        log.info("字符 {} 是否为ASCII字符：{}", symbol, CharUtils.isAscii(symbol));
        log.info("字符 {} 是否为ASCII字符：{}", number, CharUtils.isAscii(number));
        log.info("字符 {} 是否为可打印ASCII字符：{}", alpha, CharUtils.isAsciiPrintable(alpha));
        log.info("字符 {} 是否为ASCII字符：{}", symbol, CharUtils.isAsciiPrintable(symbol));
        log.info("字符 {} 是否为ASCII字符：{}", number, CharUtils.isAsciiPrintable(number));

        // 判断是否为数字字符
        log.info("字符 {} 是否为数字字符：{}", alpha, CharUtils.isAsciiNumeric(alpha));
        log.info("字符 {} 是否为数字字符：{}", symbol, CharUtils.isAsciiNumeric(symbol));
        log.info("字符 {} 是否为数字字符：{}", number, CharUtils.isAsciiNumeric(number));

        // 判断是否为字母字符
        log.info("字符 {} 是否为字母字符：{}", alpha, CharUtils.isAsciiAlpha(alpha));
        log.info("字符 {} 是否为字母字符：{}", symbol, CharUtils.isAsciiAlpha(symbol));
        log.info("字符 {} 是否为字母字符：{}", number, CharUtils.isAsciiAlpha(number));

        // 测试字符比较（忽略大小写）
        log.info("字符 {} 和 {} 是否相等：{}", alpha, symbol, CharUtils.compare('A', 'a'));

        // 测试toCharacterObject方法
        Character convertedToCharacterObject = CharUtils.toCharacterObject("A");
        log.info("字符 {} 转换结果：{}", "A", convertedToCharacterObject);

        // 测试toChar方法
        char convertedToChar = CharUtils.toChar("B");
        log.info("字符 {} 转换结果：{}", "B", convertedToChar);

        // 测试unicode转义
        String unicodeEscaped = CharUtils.unicodeEscaped('\'');
        log.info("字符 {} 转换结果：{}", "'", unicodeEscaped);
    }
}
