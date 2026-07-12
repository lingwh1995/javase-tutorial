package org.bluebridge.cas;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板字符串
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class _04_TemplateLiteralsTest {

    /**
     * 测试模板字符串-基于Java语言
     */
    @Test
    public void testTemplateLiteralsBasedOnJavaLanguage() {
        // 使用String.format()方式实现
        String name = "Alice";
        int age = 23;
        String greeting = String.format("Hello, %s! You are %d years old.", name, age);
        System.out.println(greeting);

        // 使用MessageFormat.format()方式实现
        name = "Bob";
        age = 25;
        String pattern = "Hello, {0}! You are {1} years old.";
        greeting = MessageFormat.format(pattern, name, age);
        System.out.println(greeting);
    }

    /**
     * 测试模板字符串-基于Apache Commons Lang3
     */
    @Test
    public void testTemplateLiteralsBasedOnApacheCommonsLang3() {
        Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("name", "Alice");
        valuesMap.put("age", 30);
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        String greeting = sub.replace("Hello, ${name}! You are ${age} years old.");
        System.out.println(greeting);
    }

    /**
     * 测试模板字符串-基于Java11文本块儿 + String.format()
     */
    @Test
    public void testTemplateLiteralsBasedOnJava11() {
        String name = "Alice";
        int age = 30;
        // 注意：这种方式需要JDK 15及以上支持formatted方法在字符串字面量上。在Java 11到14中，需要先定义变量然后使用String.format
        String greeting = """
                Hello, %s! You are %d years old.
                """
                .formatted(name, age);
        System.out.println(greeting);
    }
}
