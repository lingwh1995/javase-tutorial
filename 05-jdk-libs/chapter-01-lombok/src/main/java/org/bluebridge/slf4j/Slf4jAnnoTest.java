package org.bluebridge.slf4j;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 测试@Slf4j注解
 *
 * @author lingwh
 * @date 2025/8/18 13:45
 */
@Slf4j
public class Slf4jAnnoTest {

    /**
     * 测试@Slf4j注解
     */
    @Test
    public void testSlf4jAnno() {
        log.info("测试@Slf4j注解......");
        log.debug("测试@Slf4j注解......");
    }
}
