package org.bluebridge.getter_and_setter.setter;

import org.junit.Test;

/**
 * @author lingwh
 * @desc 测试@Setter注解
 * @date 2025/8/18 11:54
 */
public class SetterAnnoTest {

    /**
     * 测试@Setter注解
     */
    @Test
    public void testSetterAnno() {
        User user = new User();
        user.setId("001");
        user.setName("张三");
        user.setAge(18);
        // 私有的Setter方法外部无法访问
        // user.setEmail("123@gmail.com");
    }
}
