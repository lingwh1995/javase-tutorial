package org.bluebridge.section_08_jdk8_lts.unit_11_type_annotation;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * Java8 新增类型注解（JEP 104）
 *
 * 演化历程：
 *  Java 8 之前：注解只能用于声明（如类、方法、字段等），不能用于类型使用处
 *  Java 8 及以后：通过新增 ElementType.TYPE_USE 和 ElementType.TYPE_PARAMETER，注解可以用于任何使用类型的地方，
 *  包括泛型、类型转换、继承、throws 等，为编译时类型检查提供了基础
 *
 *
 * @author lingwh
 * @date 2026/08/06 10:00
 */
public class TypeAnnotationTest {

    /**
     * 自定义非空类型注解，可用于任何类型使用处
     */
    @Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NonNull {
    }

    // 在类型上使用注解：@NonNull String
    private @NonNull String name = "Alice";

    // 在泛型上使用注解：List<@NonNull String>
    private List<@NonNull String> list = new ArrayList<>();

    // 在构造器上使用注解
    public @NonNull TypeAnnotationTest() {
    }

    /**
     * 测试：在类型转换（cast）上使用注解
     */
    @Test
    public void testTypeAnnotationOnCast() {
        Object obj = "hello";
        // 在类型转换上使用类型注解
        String str = (@NonNull String) obj;
        System.out.println(str);
    }

    /**
     * 测试：在继承（extends）上使用注解
     */
    @Test
    public void testTypeAnnotationOnExtends() {
        // 在继承上使用类型注解
        class MyList extends @NonNull ArrayList<@NonNull String> {
        }
        MyList myList = new MyList();
        myList.add("hello");
        System.out.println(myList.get(0));
    }

    /**
     * 测试：在 throws 上使用注解
     */
    @Test
    public void testTypeAnnotationOnThrows() throws @NonNull Exception {
        // 在异常声明上使用类型注解
        throw new Exception("测试类型注解在 throws 上");
    }

    /**
     * 测试：在泛型上使用注解
     */
    @Test
    public void testTypeAnnotationOnGeneric() {
        // 在泛型类型上使用注解
        List<@NonNull String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        for (@NonNull String name : names) {
            System.out.println(name);
        }
    }
}