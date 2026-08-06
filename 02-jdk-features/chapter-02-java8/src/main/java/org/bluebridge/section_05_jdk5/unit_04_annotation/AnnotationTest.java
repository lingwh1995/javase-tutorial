package org.bluebridge.section_05_jdk5.unit_04_annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * JDK1.5 注解测试
 *
 * 注解(Annotation)是 JDK1.5 引入的, 为程序提供元数据(描述数据的数据), 本身不改变程序的逻辑,
 * 可以通过反射读取注解信息并做出相应处理(如 Spring 框架的 @Component 等)
 * 1. 自定义注解: 使用 @interface 关键字定义, 注解中的"方法"实际上是注解的属性,
 *    属性的类型可以是基本类型、String、Class、枚举、注解及这些类型的数组
 * 2. 元注解: 用于注解其他注解的注解
 *    @Target: 指定注解可以应用的位置(类、方法、字段等)
 *    @Retention: 指定注解的保留策略(SOURCE 源码、CLASS 字节码、RUNTIME 运行时)
 *    @Documented: 指定注解是否包含在 Javadoc 中
 *    @Inherited: 指定注解是否可以被子类继承(仅对类生效)
 * 3. 通过反射读取注解: Class.getAnnotation() / isAnnotationPresent() 等
 *
 * @author lingwh
 * @date 2026/08/05 18:26
 */
public class AnnotationTest {

    /**
     * 自定义注解: 用于描述类、方法或字段的信息
     * @Target({TYPE, METHOD, FIELD}): 该注解可以用于类、方法、字段的声明上
     * @Retention(RUNTIME): 该注解保留到运行时, 可以通过反射读取
     * @Documented: 该注解会被 Javadoc 工具提取到文档中
     * @Inherited: 该注解可以被标注类的子类继承
     */
    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Inherited
    @interface MyInfo {
        // 注解属性: 定义方式类似方法, 可以有默认值
        String value() default "默认信息";

        int level() default 1;
    }

    /**
     * 使用自定义注解标注的父类: 演示 @Inherited 的继承效果
     */
    @MyInfo(value = "父类的注解信息", level = 5)
    static class ParentClass {
    }

    /**
     * 子类: 未直接标注 @MyInfo, 但由于 @MyInfo 标注了 @Inherited, 子类可以继承该注解
     */
    static class ChildClass extends ParentClass {
    }

    /**
     * 在方法上使用自定义注解的类
     */
    static class AnnotatedMethodClass {
        @MyInfo(value = "方法的注解信息", level = 3)
        public void process() {
            System.out.println("执行 process() 方法");
        }
    }

    /**
     * 测试自定义注解的定义与使用: 注解属性、默认值、@Inherited 继承效果
     */
    @Test
    public void testAnnotationDefinitionAndUse() {
        // 通过反射查看注解类中定义的属性
        System.out.println("MyInfo 注解的属性: ");
        for (Method method : MyInfo.class.getDeclaredMethods()) {
            System.out.println("  属性名: " + method.getName() + ", 返回值类型: " + method.getReturnType().getSimpleName());
        }
        System.out.println("--------------------------------------");
        // 读取父类上的注解
        MyInfo parentInfo = ParentClass.class.getAnnotation(MyInfo.class);
        System.out.println("父类上的注解: value = " + parentInfo.value() + ", level = " + parentInfo.level());
        // 子类未直接标注注解, 但由于 @Inherited 元注解, 子类继承了父类的注解
        MyInfo childInfo = ChildClass.class.getAnnotation(MyInfo.class);
        System.out.println("子类继承到的注解: value = " + childInfo.value() + ", level = " + childInfo.level());
        System.out.println("子类是否拥有 @MyInfo 注解: " + ChildClass.class.isAnnotationPresent(MyInfo.class));
    }

    /**
     * 测试通过反射读取方法上的注解信息
     */
    @Test
    public void testReadAnnotationByReflection() {
        try {
            // 通过反射获取方法对象
            Method method = AnnotatedMethodClass.class.getMethod("process");
            // 判断方法是否标注了指定注解
            boolean hasAnnotation = method.isAnnotationPresent(MyInfo.class);
            System.out.println("process() 方法是否标注 @MyInfo: " + hasAnnotation);
            // 获取注解实例并读取属性值
            MyInfo info = method.getAnnotation(MyInfo.class);
            if (info != null) {
                System.out.println("注解属性 value: " + info.value());
                System.out.println("注解属性 level: " + info.level());
            }
            System.out.println("--------------------------------------");
            // 获取方法上所有注解(含 JDK 内置注解)
            Annotation[] annotations = method.getAnnotations();
            System.out.println("process() 方法上的所有注解: ");
            for (Annotation annotation : annotations) {
                System.out.println("  " + annotation.annotationType().getName());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试元注解: 查看 JDK 内置注解上使用的元注解信息
     */
    @Test
    public void testMetaAnnotation() {
        // 通过反射查看 JDK 内置注解 @Deprecated 上标注的元注解
        Annotation[] annotations = Deprecated.class.getAnnotations();
        System.out.println("@Deprecated 注解上标注的元注解: ");
        for (Annotation annotation : annotations) {
            System.out.println("  " + annotation);
        }
        System.out.println("--------------------------------------");
        // 查看 @Override 上标注的元注解: @Target 和 @Retention(SOURCE)
        Annotation[] overrideAnnotations = Override.class.getAnnotations();
        System.out.println("@Override 注解上标注的元注解: ");
        for (Annotation annotation : overrideAnnotations) {
            System.out.println("  " + annotation);
        }
        System.out.println("--------------------------------------");
        // 说明: @Target 限制注解的使用位置, @Retention 决定注解的生命周期,
        // @Inherited 决定注解是否被子类继承, @Documented 决定注解是否进入 Javadoc
        System.out.println("@Override 的保留策略是 SOURCE, 仅存在于源码中, 不会进入字节码和运行时");
    }
}
