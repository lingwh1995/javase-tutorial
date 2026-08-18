package org.bluebridge.section_01_basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * java 标识符命名规范
 *
 * 1. 由 26 个英文字母大小写，0-9 ，_或 $ 组成
 * 2. 数字不可以开头。
 * 3. 不可以使用关键字和保留字，但能包含关键字和保留字。
 * 4. Java 中严格区分大小写，长度无限制。
 * 5. 标识符不能包含空格。
 *
 * @author lingwh
 * @date 2019/3/12 16:58
 */
@Slf4j
public class IdentifierTest {

    /**
     * 测试标识符
     */
    @Test
    public void testIdentifier() {
        // 标识符可以使用$开头
        int $i = 10;
        log.info("$i: {}", $i);

        // 为什么标识符不能以数字开头?
        // 如果允许数字开头，则如下的声明编译就可以通过：
        // int 123L = 12;
        // 进而，如下的声明中 l 的值到底是 123？还是变量 123L 对应的取值 12 呢？ 出现歧义了。
        // long l = 123L;
    }
}
