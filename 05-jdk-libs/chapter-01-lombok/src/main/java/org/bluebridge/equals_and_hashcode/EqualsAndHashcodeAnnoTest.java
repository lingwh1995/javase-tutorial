package org.bluebridge.equals_and_hashcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 测试@EqualsAndHashCode注解
 *
 * @author lingwh
 * @date 2025/8/18 11:47
 */
@Slf4j
public class EqualsAndHashcodeAnnoTest {

    @Test
    public void testEqualsAndHashcodeAnno() {
        User user = new User();
        log.info("user.hashCode(): {}", user.hashCode());
    }
}
