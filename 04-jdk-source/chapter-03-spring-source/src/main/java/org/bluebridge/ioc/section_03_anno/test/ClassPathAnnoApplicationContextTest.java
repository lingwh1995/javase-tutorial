package org.bluebridge.ioc.section_03_anno.test;

import org.bluebridge.ioc.section_03_anno.factory.ClassPathAnnoApplicationContext;

/**
 * 注解方式应用上下文测试
 *
 * @author lingwh
 * @date 2019/3/20 19:02
 */
public class ClassPathAnnoApplicationContextTest {

    public static void main(String[] args) {
        ClassPathAnnoApplicationContext applicationContext =
                new ClassPathAnnoApplicationContext("applicationContext-ioc-anno-one.xml");
    }
}
