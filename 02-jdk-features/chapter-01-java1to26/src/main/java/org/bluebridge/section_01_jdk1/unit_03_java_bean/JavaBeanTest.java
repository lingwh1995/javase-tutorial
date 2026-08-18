package org.bluebridge.section_01_jdk1.unit_03_java_bean;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * JDK 1.1 JavaBeans 组件模型测试
 *
 * JDK 1.1 引入 java.beans 包, 提供了 JavaBeans 组件模型规范:
 * 1. JavaBean 规范: 类必须有公共无参构造方法, 属性必须私有化并通过公共 getter/setter 方法访问
 * 2. Introspector: 内省器, 通过分析 getter/setter 方法自动发现 JavaBean 的属性信息
 * 3. PropertyDescriptor: 属性描述符, 封装了属性的类型、读写方法等信息
 * JavaBeans 是后续 EL 表达式、Spring Bean、MyBatis 参数映射等技术的基础
 *
 * @author lingwh
 * @date 2026/08/05 18:25
 */
public class JavaBeanTest {

    /**
     * 符合 JavaBean 规范的实体类: 私有字段 + 公共 getter/setter
     */
    static class Person {

        // 私有字段 name
        private String name;

        // 私有字段 age
        private int age;

        /**
         * JavaBean 规范: 必须提供公共无参构造方法
         */
        public Person() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }

    /**
     * 测试 JavaBean 的基本规范: 私有字段 + 公共 getter/setter
     */
    @Test
    public void testJavaBeanBasic() {
        // JavaBean 通过公共无参构造方法创建对象
        Person person = new Person();
        // 通过 setter 方法设置属性值
        person.setName("张三");
        person.setAge(25);
        // 通过 getter 方法读取属性值
        System.out.println("JavaBean 属性: name = " + person.getName() + ", age = " + person.getAge());
    }

    /**
     * 测试通过 Introspector 内省 JavaBean 的属性信息
     */
    @Test
    public void testIntrospector() throws IntrospectionException {
        // 通过 Introspector 获取 JavaBean 的完整描述信息
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        System.out.println("Person 类的属性描述信息: ");
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            // 排除 Object 类自带的 class 属性
            if ("class".equals(propertyDescriptor.getName())) {
                continue;
            }
            System.out.println("属性名: " + propertyDescriptor.getName()
                    + ", 属性类型: " + propertyDescriptor.getPropertyType().getName()
                    + ", 读方法: " + propertyDescriptor.getReadMethod().getName()
                    + ", 写方法: " + propertyDescriptor.getWriteMethod().getName());
        }
    }

    /**
     * 测试通过 PropertyDescriptor 反射读写属性值
     */
    @Test
    public void testPropertyDescriptor() throws Exception {
        Person person = new Person();
        // 通过 PropertyDescriptor 获取写方法并设置 name 属性值
        PropertyDescriptor nameDescriptor = new PropertyDescriptor("name", Person.class);
        nameDescriptor.getWriteMethod().invoke(person, "李四");
        // 通过 PropertyDescriptor 获取读方法并读取 name 属性值
        Object name = nameDescriptor.getReadMethod().invoke(person);
        System.out.println("通过 PropertyDescriptor 读写 name: " + name);
        // 通过 PropertyDescriptor 读写 age 属性
        PropertyDescriptor ageDescriptor = new PropertyDescriptor("age", Person.class);
        ageDescriptor.getWriteMethod().invoke(person, 30);
        Object age = ageDescriptor.getReadMethod().invoke(person);
        System.out.println("通过 PropertyDescriptor 读写 age: " + age);
        System.out.println("反射读写后的完整对象: " + person);
    }
}
