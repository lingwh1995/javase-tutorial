package org.bluebridge.section_18_jdk18.unit_05_other;

import org.junit.Test;

import java.lang.annotation.*;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * JDK 18 其他特性测试
 *
 * 涵盖 JDK 18 中的多项改进:
 * 1. @Repeatable 注解的改进 - 容器注解不再需要显式声明 @Retention(RUNTIME)
 * 2. java.lang.reflect.AnnotatedType 的改进 - 对注解类型的反射支持增强
 * 3. java.lang.Deprecated 注解的改进 (forRemoval / since 属性增强)
 *
 * @author lingwh
 * @date 2026/08/05 19:11
 */
public class OtherFeaturesTest {

    /**
     * 测试 JDK 18 中 @Repeatable 注解的改进
     * JDK 18 改进了 @Repeatable 注解的处理: 容器注解类型不再需要
     * 显式声明 @Retention(RetentionPolicy.RUNTIME), 而是自动继承
     * 可重复注解的保留策略。这简化了可重复注解的声明和使用。
     */
    @Test
    public void testRepeatableAnnotationImprovement() throws Exception {
        System.out.println("=== @Repeatable 注解改进测试 ===");

        // 获取类上的重复注解
        RepeatableInfo[] annotations = AnnotatedClass.class.getAnnotationsByType(RepeatableInfo.class);
        System.out.println("获取到的重复注解数量: " + annotations.length);
        for (RepeatableInfo info : annotations) {
            System.out.println("  注解值: " + info.value());
        }

        // 验证可以获取到容器注解
        RepeatableContainer container = AnnotatedClass.class.getAnnotation(RepeatableContainer.class);
        System.out.println("容器注解是否存在: " + (container != null));
        if (container != null) {
            System.out.println("容器注解中的值数量: " + container.value().length);
        }

        // 验证通过 getAnnotationsByType 可以获取到所有重复注解
        assert annotations.length == 3 : "应获取到 3 个重复注解";
        System.out.println("验证通过: 成功获取到所有重复注解");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 JDK 18 中 AnnotatedType 的改进
     * JDK 18 改进了 AnnotatedType 接口, 增强了对泛型类型注解的反射支持。
     * 可以通过 AnnotatedParameterizedType 获取泛型类型参数的注解,
     * 通过 AnnotatedArrayType 获取数组组件类型的注解。
     */
    @Test
    public void testAnnotatedTypeImprovement() throws Exception {
        System.out.println("=== AnnotatedType 改进测试 ===");

        // 获取字段的 AnnotatedType
        Field stringField = TypeAnnotatedClass.class.getField("annotatedField");
        AnnotatedType annotatedType = stringField.getAnnotatedType();
        System.out.println("字段 annotatedField 的 AnnotatedType: " + annotatedType.getClass().getSimpleName());
        System.out.println("字段类型: " + annotatedType.getType());

        // 如果 AnnotatedType 是参数化类型, 可以获取类型参数上的注解
        if (annotatedType instanceof AnnotatedParameterizedType) {
            AnnotatedParameterizedType parameterizedType = (AnnotatedParameterizedType) annotatedType;
            AnnotatedType[] actualTypeArguments = parameterizedType.getAnnotatedActualTypeArguments();
            System.out.println("泛型类型参数数量: " + actualTypeArguments.length);
            for (int i = 0; i < actualTypeArguments.length; i++) {
                System.out.println("  类型参数 [" + i + "]: " + actualTypeArguments[i].getType());
                System.out.println("  类型参数注解: " + Arrays.toString(actualTypeArguments[i].getAnnotations()));
            }
        }

        // 获取方法返回值的 AnnotatedType
        Method method = TypeAnnotatedClass.class.getMethod("annotatedMethod");
        AnnotatedType returnType = method.getAnnotatedReturnType();
        System.out.println("方法返回值的 AnnotatedType: " + returnType.getClass().getSimpleName());
        System.out.println("返回类型: " + returnType.getType());

        // 获取方法参数的 AnnotatedType
        AnnotatedType[] parameterTypes = method.getAnnotatedParameterTypes();
        System.out.println("方法参数数量: " + parameterTypes.length);
        for (int i = 0; i < parameterTypes.length; i++) {
            System.out.println("  参数 [" + i + "]: " + parameterTypes[i].getType());
        }

        System.out.println("--------------------------------------");
    }

    /**
     * 测试 AnnotatedArrayType 的数组类型注解
     * 演示通过 AnnotatedArrayType 获取数组组件类型的注解
     */
    @Test
    public void testAnnotatedArrayType() throws Exception {
        System.out.println("=== AnnotatedArrayType 测试 ===");

        Field arrayField = TypeAnnotatedClass.class.getField("annotatedArray");
        AnnotatedType annotatedType = arrayField.getAnnotatedType();
        System.out.println("字段 annotatedArray 的 AnnotatedType: " + annotatedType.getClass().getSimpleName());

        // 如果是数组类型, 可以获取组件类型的注解
        if (annotatedType instanceof AnnotatedArrayType) {
            AnnotatedArrayType annotatedArrayType = (AnnotatedArrayType) annotatedType;
            AnnotatedType componentType = annotatedArrayType.getAnnotatedGenericComponentType();
            System.out.println("数组组件类型: " + componentType.getType());
            System.out.println("数组组件类型注解: " + Arrays.toString(componentType.getAnnotations()));
        }

        System.out.println("--------------------------------------");
    }

    /**
     * 测试 JDK 18 中 @Deprecated 注解的改进
     * JDK 18 中 @Deprecated 注解的 forRemoval 和 since 属性得到了更广泛的应用,
     * 用于标记在不同版本中废弃的 API
     */
    @Test
    public void testDeprecatedAnnotationAttributes() throws Exception {
        System.out.println("=== @Deprecated 注解属性测试 ===");

        // 获取 DeprecatedClass 上的 @Deprecated 注解
        Deprecated deprecated = DeprecatedClass.class.getAnnotation(Deprecated.class);
        if (deprecated != null) {
            System.out.println("DeprecatedClass 的 since 属性: " + deprecated.since());
            System.out.println("DeprecatedClass 的 forRemoval 属性: " + deprecated.forRemoval());
        }

        // 获取 deprecatedMethod 上的 @Deprecated 注解
        Method deprecatedMethod = DeprecatedClass.class.getMethod("deprecatedMethod");
        Deprecated methodDeprecated = deprecatedMethod.getAnnotation(Deprecated.class);
        if (methodDeprecated != null) {
            System.out.println("deprecatedMethod 的 since 属性: " + methodDeprecated.since());
            System.out.println("deprecatedMethod 的 forRemoval 属性: " + methodDeprecated.forRemoval());
        }

        // 获取 deprecatedField 上的 @Deprecated 注解
        Field deprecatedField = DeprecatedClass.class.getField("deprecatedField");
        Deprecated fieldDeprecated = deprecatedField.getAnnotation(Deprecated.class);
        if (fieldDeprecated != null) {
            System.out.println("deprecatedField 的 since 属性: " + fieldDeprecated.since());
            System.out.println("deprecatedField 的 forRemoval 属性: " + fieldDeprecated.forRemoval());
        }

        System.out.println("--------------------------------------");
    }

    /**
     * 测试 JDK 18 中注解的综合反射操作
     * 演示通过反射获取类、方法、字段上的所有注解
     */
    @Test
    public void testAnnotationReflection() throws Exception {
        System.out.println("=== 注解反射综合测试 ===");

        // 获取类上的所有注解
        Annotation[] classAnnotations = ComplexAnnotationClass.class.getAnnotations();
        System.out.println("类上的注解数量: " + classAnnotations.length);
        for (Annotation annotation : classAnnotations) {
            System.out.println("  注解: " + annotation.annotationType().getSimpleName() + " = " + annotation);
        }

        // 获取方法上的所有注解
        Method method = ComplexAnnotationClass.class.getMethod("annotatedMethod");
        Annotation[] methodAnnotations = method.getAnnotations();
        System.out.println("方法上的注解数量: " + methodAnnotations.length);
        for (Annotation annotation : methodAnnotations) {
            System.out.println("  注解: " + annotation.annotationType().getSimpleName() + " = " + annotation);
        }

        // 获取字段上的所有注解
        Field field = ComplexAnnotationClass.class.getField("annotatedField");
        Annotation[] fieldAnnotations = field.getAnnotations();
        System.out.println("字段上的注解数量: " + fieldAnnotations.length);
        for (Annotation annotation : fieldAnnotations) {
            System.out.println("  注解: " + annotation.annotationType().getSimpleName() + " = " + annotation);
        }

        System.out.println("--------------------------------------");
    }
}

// === 可重复注解定义 ===

/**
 * 可重复注解
 */
@Repeatable(RepeatableContainer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface RepeatableInfo {
    String value();
}

/**
 * 容器注解 - JDK 18 改进: 不再需要显式声明 @Retention(RUNTIME)
 * 容器注解的保留策略自动继承自 @Repeatable 注解的保留策略
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface RepeatableContainer {
    RepeatableInfo[] value();
}

/**
 * 使用重复注解的类
 */
@RepeatableInfo("JDK 18")
@RepeatableInfo("JEP 400")
@RepeatableInfo("UTF-8 Default")
class AnnotatedClass {
}

// === 类型注解定义 ===

/**
 * 类型注解, 用于测试 AnnotatedType
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@interface TypeAnnotation {
    String value() default "";
}

/**
 * 使用类型注解的类, 用于测试 AnnotatedType 反射
 */
class TypeAnnotatedClass {
    @TypeAnnotation("field")
    public String annotatedField;

    @TypeAnnotation("array")
    public String @TypeAnnotation("array-component") [] annotatedArray;

    @TypeAnnotation("return")
    public @TypeAnnotation("method") String annotatedMethod(@TypeAnnotation("param") String param) {
        return param;
    }
}

/**
 * 使用 @Deprecated 注解的类
 */
@Deprecated(since = "18", forRemoval = false)
class DeprecatedClass {
    @Deprecated(since = "18", forRemoval = true)
    public String deprecatedField;

    @Deprecated(since = "17", forRemoval = false)
    public void deprecatedMethod() {
        System.out.println("这是一个废弃的方法");
    }
}

/**
 * 综合注解测试类
 */
@RepeatableInfo("Complex")
@RepeatableInfo("Annotation")
@Deprecated(since = "18")
class ComplexAnnotationClass {
    @Deprecated
    public String annotatedField;

    @Deprecated(since = "18", forRemoval = true)
    public void annotatedMethod() {
        System.out.println("注解反射测试方法");
    }
}