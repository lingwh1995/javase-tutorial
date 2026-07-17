package org.bluebridge.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 退出while循环测试
 *
 * @author lingwh
 * @date 2025/5/3 16:58
 */
@Slf4j
public class QuitWhileLoopTest {

    /**
     * 测试推出while循环
     */
    @Test
    public void testQuitWhileLoop() {
        boolean loop = true;
        int i = 0;
        while (loop) {
            log.info("i: {}", i);
            i++;
            if (i == 20) {
                loop = false;
            }
        }
    }
}
