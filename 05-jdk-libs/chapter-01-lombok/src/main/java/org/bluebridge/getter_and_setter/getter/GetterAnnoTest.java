package org.bluebridge.getter_and_setter.getter;

import org.junit.Test;

/**
 * 测试@Getter注解
 *
 * @author lingwh
 * @date 2025/8/18 11:51
 */
public class GetterAnnoTest {

    /**
     * 测试@Getter注解
     */
    @Test
    public void testGetterAnno() {
        User user = new User();
        user.getId();
        user.getName();
        user.getAge();
        // 私有的Getter方法外部无法访问
        // user.getEmail();
    }
}
