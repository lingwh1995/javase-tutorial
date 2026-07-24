package org.bluebridge.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * i++ 和 ++i 测试
 *
 * @author lingwh
 * @date 2025/5/3 16:58
 */
@Slf4j
public class PPiandiPPTest {

    /**
     * 测试 i++ 和 ++i
     */
    @Test
    public void testPPiandiPP() {
        int i = 0;
        log.info("++i: {}", ++i);
        int j = 0;
        log.info("j++: {}", j++);
        log.info("----------------------------------");

        int a = 10, b = 10;
        int x = ++a;
        log.info("x: {}", x);
        log.info("a: {}", a);
        x = b++;
        log.info("x: {}", x);
        log.info("b: {}", b);
    }
}
