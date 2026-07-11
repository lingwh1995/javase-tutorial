package org.bluebridge.constructor.required_args_constructor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 测试@RequiredArgsConstructor注解
 * @date 2025/8/18 11:38
 */
@Slf4j
public class RequiredArgsConstructorAnnoTest {

    /**
     * 测试@RequiredArgsConstructor注解
     */
    @Test
    public void testRequiredArgsConstructorAnno() {
        User user = new User("001", "张三");
        log.info("user: {}", user);
    }
}
