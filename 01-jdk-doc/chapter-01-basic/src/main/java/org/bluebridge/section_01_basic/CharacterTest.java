package org.bluebridge.section_01_basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 字符测试类
 *
 * @author lingwh
 * @date 2025/11/7 13:35
 */
@Slf4j
public class CharacterTest {

    /**
     * 方法说明
     *   功能： 返回指定字符的 int 数值
     *   参数： 接受一个 char 类型的字符参数
     *   返回值： 返回字符对应的数值，如果字符没有数值则返回 -1
     *
     * 数值规则
     *   数字字符： '0'-'9' 返回 0-9
     *   字母字符：
     *   'a'-'z' 返回 10-35
     *   'A'-'Z' 返回 10-35
     *   其他字符： 如汉字、特殊符号等通常返回 -1
     */
    @Test
    public void testCharacter() {
        char c = '0';
        int numericValue = Character.getNumericValue(c);
        log.info("字符 {} 的数值为 {}", c, numericValue);

        c = '1';
        numericValue = Character.getNumericValue(c);
        log.info("字符 {} 的数值为 {}", c, numericValue);

        c = 'a';
        numericValue = Character.getNumericValue(c);
        log.info("字符 {} 的数值为 {}", c, numericValue);

        c = 'A';
        numericValue = Character.getNumericValue(c);
        log.info("字符 {} 的数值为 {}", c, numericValue);

        c = 'x';
        numericValue = Character.getNumericValue(c);
        log.info("字符 {} 的数值为 {}", c, numericValue);

        c = '你';
        numericValue = Character.getNumericValue(c);
        log.info("字符 {} 的数值为 {}", c, numericValue);

        c = '_';
        numericValue = Character.getNumericValue(c);
        log.info("字符 {} 的数值为 {}", c, numericValue);

        c = 1;
        numericValue = Character.getNumericValue(c);
        log.info("字符 {} 的数值为 {}", c, numericValue);
    }
}
