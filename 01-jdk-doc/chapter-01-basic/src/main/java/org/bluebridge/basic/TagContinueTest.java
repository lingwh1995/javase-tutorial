package org.bluebridge.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模拟 goto - 打印 101 到 150 之间的质数测试
 *
 * @author lingwh
 * @date 2019/4/10 13:39
 */
@Slf4j
public class TagContinueTest {

    @Test
    public void testTagContinue() {
        outer:
        for (int i = 101; i < 150; i++) {
            for (int j = 2; j < i / 2; j++) {
                if (i % j == 0) {
                    continue outer;
                }
            }
            log.info("{}", i);
        }
    }
}
