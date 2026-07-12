package org.bluebridge.constructor.all_args_constructor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 测试@AllArgsConstructor注解
 *
 * @author lingwh
 * @date 2025/8/18 11:30
 */
@Slf4j
public class AllArgsConstructorAnnoTest {

    /**
     * 测试@AllArgsConstructor注解
     */
    @Test
    public void testAllArgsConstructorAnno() {
        User user = new User("1", "zhangsan", 18, "zhangsan@qq.com");
        log.info("user: {}", user);
    }
}
