package org.bluebridge.reflect.section_06_reflect_demo.unit_03_my_orm;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * ORM注解测试
 *
 * @author lingwh
 * @date 2026/6/22 18:04
 */
public class OrmTest {

    /**
     * 测试自己写的出现在ORM框架中常见注解
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void testOrm() throws ClassNotFoundException {
        Class<? extends Object> clazz = Class.forName("org.bluebridge.reflect.section_06_reflect_demo.unit_03_my_orm.Student");
        // 获取该类上所有注解
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        // 获取该类上指定名称的属性
        Table table = clazz.getAnnotation(Table.class);
        System.out.println(table.value());

        // 获取该类中的字段名称和该字段上所加的指定注解
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            System.out.println("属性名称:" + field.getName() + "---该属性上的注解:" + column.toString());
        }
        // 根据获取到的属性名称和属性上的注解的信息拼接出SQL语句，使用JDBC执行该语句完成ORM映射
    }
}
