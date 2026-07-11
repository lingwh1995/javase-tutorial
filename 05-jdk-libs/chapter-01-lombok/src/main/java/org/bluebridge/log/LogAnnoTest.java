package org.bluebridge.log;

import lombok.extern.java.Log;
import org.junit.Test;

/**
 * 测试@Log注解 @Log 使用的是 Java 原生的 java.util.logging 框架
 *
 * 如果需要使用其他日志框架，可以使用相应的注解
 * @Slf4j - SLF4J
 * @Log4j - Log4j
 * @Log4j2 - Log4j2
 *
 * @author lingwh
 * @date 2025/8/18 12:00
 */
@Log
public class LogAnnoTest {

    @Test
    public void testLogAnno() {
        log.info("测试@Log注解......");
    }
}
