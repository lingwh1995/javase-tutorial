package org.bluebridge.tostring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 测试@ToString注解
 * @date 2025/8/18 13:49
 */
@Slf4j
public class ToStringAnnoTest {

    /**
     * 测试@ToString注解
     */
    @Test
    public void testToStringAnno() {
        User user = new User();
        log.info("user:{}", user);
    }
}
