package org.bluebridge.constructor.no_args_constructor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 测试@NoArgsConstructor注解
 * @date 2025/8/18 11:34
 */
@Slf4j
public class NoArgsConstructorAnnoTest {

    /**
     * 测试@NoArgsConstructor注解
     */
    @Test
    public void testNoArgsConstructorAnno() {
        User user = new User();
        log.info("user: {}", user);
    }
}
