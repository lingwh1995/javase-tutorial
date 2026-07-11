package org.bluebridge.getter_and_setter.getter_and_setter;

import org.junit.Test;

/**
 * @author lingwh
 * @desc 测试@Getter注解和@Setter注解
 * @date 2025/8/18 11:54
 */
public class GetterAndSetterAnnoTest {

    /**
     * 测试@Getter注解和@Setter注解
     */
    @Test
    public void testGetterAndSetterAnno() {
        User user = new User();
        // 测试@Setter注解
        user.setId("001");
        user.setName("张三");

        // 被static修饰的属性外部无法使用set方法访问
        // user.setAge(18);
        // 私有的Setter方法外部无法访问
        // user.setEmail("123@gmail.com");

        // 测试@Getter注解
        user.getId();
        user.getName();
        // 被static修饰的属性外部无法使用get方法访问
        // user.getAge();
        // 私有的Getter方法外部无法访问
        // user.getEmail();
    }
}
