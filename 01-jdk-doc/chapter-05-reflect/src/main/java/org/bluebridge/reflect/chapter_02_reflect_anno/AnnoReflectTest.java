package org.bluebridge.reflect.chapter_02_reflect_anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 测试反射读取注解
 * @date 2026/7/9 00:00
 */
public class AnnoReflectTest {

    /**
     * 使用反射读取类上的注解(单个注解)
     */
    @Test
    public void testSingleAnnotationInType() {
        // 1.获取注解的作用目标
        Class<? extends Object> clazz = AnnoReflect.class;
        // 2.获取指定的注解类型(得到注解对象)
        MyAnno2 myAnno2 = clazz.getAnnotation(MyAnno2.class);
        // 3.打印注解属性的值
        System.out.println(myAnno2.name() + "---" + myAnno2.age() + "---" + myAnno2.sex());
    }

    /**
     * 使用反射读取属性上的注解/单个注解
     *
     * @throws NoSuchFieldException
     */
    @Test
    public void testSingleAnnotationInField() throws NoSuchFieldException {
        // 1.获取注解的作用目标
        Class<? extends Object> clazz = AnnoReflect.class;
        Field field = clazz.getDeclaredField("filed");
        System.out.println(field);
        // 2.获取指定的注解类型(得到注解对象)
        MyAnno2 myAnno2 = field.getAnnotation(MyAnno2.class);
        // 3.打印注解属性的值
        System.out.println(myAnno2.name() + "---" + myAnno2.age() + "---" + myAnno2.sex());
    }

    /**
     * 使用反射读取方法上的注解(单个注解)
     *
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    @Test
    public void testSingleAnnotationInMethod() throws NoSuchMethodException {
        // 1.获取注解的作用目标
        Class<? extends Object> clazz = AnnoReflect.class;
        Method method = clazz.getMethod("eat");
        // 2.获取指定的注解类型(得到注解对象)
        MyAnno2 myAnno2 = method.getAnnotation(MyAnno2.class);
        // 3.打印注解属性的值
        System.out.println(myAnno2.name() + "---" + myAnno2.age() + "---" + myAnno2.sex());
    }

    /**
     * 使用反射读取方法上的注解(多个注解)
     *
     * @throws NoSuchMethodException
     */
    @Test
    public void testMultiplyAnnotationInMethod() throws NoSuchMethodException {
        // 1.获取注解的作用目标
        Class<? extends Object> clazz = AnnoReflect.class;
        Method method = clazz.getMethod("eat");
        // 2.获取指定的注解类型(得到注解对象)
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            System.out.println(annotationType);
        }
    }
}
