package org.bluebridge.ioc.anno_one.test;

import org.bluebridge.ioc.anno_one.factory.ClassPathAnnoApplicationContext;

/**
 * @author lingwh
 * @desc 注解方式应用上下文测试
 * @date 2019/3/20 00:00
 */
public class ClassPathAnnoApplicationContextTest {
    public static void main(String[] args) {
        ClassPathAnnoApplicationContext applicationContext =
                new ClassPathAnnoApplicationContext("applicationContext-ioc-anno-one.xml");
    }
}
