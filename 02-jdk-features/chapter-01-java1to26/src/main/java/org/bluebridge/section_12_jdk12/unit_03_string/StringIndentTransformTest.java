package org.bluebridge.section_12_jdk12.unit_03_string;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * JDK 12 String.indent() 和 String.transform() 方法（STANDARD 特性）
 *      indent(n): 调整字符串的缩进，正数增加缩进，负数减少缩进
 *      transform(): 对字符串应用一个函数，返回转换结果
 *
 * 演化历程: String.indent()/transform() JDK 12 STANDARD
 *
 * @author lingwh
 * @date 2026/08/05 19:06
 */
public class StringIndentTransformTest {

    /**
     * 测试 String.indent() 增加缩进
     */
    @Test
    public void testIndentIncrease() {
        String text = "Hello";
        String result = text.indent(4);
        System.out.println("indent(4) 结果:");
        System.out.println("[" + result + "]");
    }

    /**
     * 测试 String.indent() 减少缩进
     */
    @Test
    public void testIndentDecrease() {
        String text = "        Hello";
        String result = text.indent(-4);
        System.out.println("indent(-4) 结果:");
        System.out.println("[" + result + "]");
    }

    /**
     * 测试 String.indent() 多行字符串缩进
     */
    @Test
    public void testIndentMultiLine() {
        String text = "第一行\n第二行\n第三行";
        String result = text.indent(6);
        System.out.println("多行 indent(6) 结果:");
        System.out.println(result);
    }

    /**
     * 测试 String.indent() 零值缩进（仅规范化换行符）
     */
    @Test
    public void testIndentZero() {
        String text = "Hello\nWorld";
        String result = text.indent(0);
        System.out.println("indent(0) 结果:");
        System.out.println("[" + result + "]");
    }

    /**
     * 测试 String.transform() 基本用法：字符串转换
     */
    @Test
    public void testTransformBasic() {
        String text = "hello world";
        // 转换为大写
        String upper = text.transform(String::toUpperCase);
        System.out.println("transform 转大写: " + upper);

        // 拼接字符串
        String quoted = text.transform(s -> "'" + s + "'");
        System.out.println("transform 加引号: " + quoted);
    }

    /**
     * 测试 String.transform() 链式调用
     */
    @Test
    public void testTransformChain() {
        String result = "  java 12  "
                .transform(String::trim)
                .transform(String::toUpperCase)
                .transform(s -> s.replace(" ", "_"));
        System.out.println("transform 链式调用结果: " + result);
    }

    /**
     * 测试 String.transform() 返回不同类型
     */
    @Test
    public void testTransformReturnDifferentType() {
        String text = "1,2,3,4,5";
        // 将字符串转换为整数列表
        List<Integer> numbers = text.transform(s -> List.of(s.split(",")))
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println("transform 转 List: " + numbers);
    }

    /**
     * 测试 String.transform() 解析 JSON 键值对
     */
    @Test
    public void testTransformParseKeyValue() {
        String entry = "name:张三";
        // 解析键值对并格式化
        String formatted = entry.transform(s -> {
            String[] parts = s.split(":");
            return parts[0] + " = " + parts[1];
        });
        System.out.println("transform 解析键值对: " + formatted);
    }

    /**
     * 测试 indent 和 transform 联合使用
     */
    @Test
    public void testIndentAndTransformCombined() {
        String text = "combined";
        String result = text
                .transform(String::toUpperCase)
                .indent(4)
                .transform(String::trim);
        System.out.println("indent + transform 联合使用结果:");
        System.out.println("[" + result + "]");
    }
}