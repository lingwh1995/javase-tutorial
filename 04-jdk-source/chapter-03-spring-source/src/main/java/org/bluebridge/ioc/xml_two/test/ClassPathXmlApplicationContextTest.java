package org.bluebridge.ioc.xml_two.test;

import org.bluebridge.ioc.xml_two.entity.Student;
import org.bluebridge.ioc.xml_two.factory.BeanFactory;
import org.bluebridge.ioc.xml_two.factory.ClassPathXmlApplicationContext;

/**
 * 测试ClassPathXmlApplicationContext
 *
 * @author lingwh
 * @date 2019/3/15 19:02
 */
public class ClassPathXmlApplicationContextTest {

    public static void main(String[] args) {
        /**
         * 验证单例Bean思路:实例化对象/创建对象必走无参，单例的话无参构造函数只调用一次
         */
        BeanFactory context = new ClassPathXmlApplicationContext("applicationContext-ioc-xml-two.xml");
        System.out.println(context.getBean("student"));
        System.out.println(context.getBean("person"));
        System.out.println(context.getBean("teacher"));
        Student user = context.getBean("student", Student.class);
        System.out.println(user);
        Object s1 = context.getBean("student");
        Object s2 = context.getBean("student");
        System.out.println(s1 == s2);
        // Object t1 = context.getBean("teacher");
        // Object t2 = context.getBean("teacher");
        // System.out.println(t1 == t2);
        // Object p1 = context.getBean("person");
        // Object p2 = context.getBean("person");
        // System.out.println(p1 == p2);
    }
}
