package org.bluebridge.builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 测试@Builder注解
 * @date 2025/8/18 11:26
 */
@Slf4j
public class BuilderAnnoTest {

    /**
     * 测试@Builder注解
     */
    @Test
    public void testBuilderAnno() {
        User user = User.builder()
                        .id("001")
                        .name("张三")
                        .age(18)
                        .email("123@gmail.com")
                        .build();
        log.info("user: {}", user);
    }
}
