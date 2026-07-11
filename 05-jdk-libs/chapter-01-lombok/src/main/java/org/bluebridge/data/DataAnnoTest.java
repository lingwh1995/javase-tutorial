package org.bluebridge.data;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 测试@Data注解
 * @date 2025/8/18 11:43
 */
@Slf4j
public class DataAnnoTest {

    /**
     * 测试@Data注解
     */
    @Test
    public void testDataAnno() {
        User user = new User();
        user.setId("001");
        user.setName("zhangsan");
        user.setAge(18);
        user.setEmail("zhangsan@163.com");
        log.info("user: {}", user);
    }
}
