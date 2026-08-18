package org.bluebridge.section_17_jdk17_lts.unit_03_instanceof_pattern;

import org.junit.Test;

import java.util.List;

/**
 * JDK 17 instanceof 模式匹配测试(STANDARD 正式特性, JEP 394, 从 JDK 16 转正)
 *
 * instanceof 模式匹配在 JDK 16 中转正为 STANDARD 正式特性, 不再需要 --enable-preview 参数。
 * 它允许在 instanceof 判断时直接声明类型模式变量, 省去后续的强制类型转换。
 *
 * 传统写法: if (obj instanceof String) { String s = (String) obj; ... }
 * 模式匹配: if (obj instanceof String s) { ... }  // 判断通过后 s 直接作为 String 使用
 *
 * 模式变量作用域规则:
 * 1. 模式变量在 &amp;&amp; 的右侧可见, 可以继续参与条件判断
 * 2. 模式变量在 || 的右侧不可见(编译错误), 因为 || 右侧在 instanceof 为 false 时也会执行
 * 3. 模式变量在 if 语句块、! 表达式之后的 else 分支中可见
 *
 * @author lingwh
 * @date 2026/08/06 09:18
 */
public class InstanceofPatternTest {

    /**
     * 测试 instanceof 模式匹配基本用法: if (obj instanceof String s)(STANDARD)
     * 判断通过后模式变量 s 直接作为 String 使用, 无需强制类型转换
     */
    @Test
    public void testInstanceofPatternMatching() {
        Object obj = "Hello JDK17";

        // ===== 旧版实现方式(JDK 16 之前): instanceof + 强制类型转换 =====
        // if (obj instanceof String) {
        //     String str = (String) obj;
        //     System.out.println("传统写法: 字符串长度 = " + str.length());
        // }
        // ===== 新版实现方式(JDK 16 起): instanceof 模式匹配, 判断通过后 s 直接可用 =====
        // 真实模式匹配语法: 判断通过后模式变量 s 直接可用
        if (obj instanceof String s) {
            System.out.println("模式匹配: 字符串内容 = " + s);
            System.out.println("模式匹配: 字符串长度 = " + s.length());
            System.out.println("模式匹配: 大写形式 = " + s.toUpperCase());
        }
    }

    /**
     * 测试 instanceof 模式匹配结合 &amp;&amp; 运算符(STANDARD)
     * 模式变量在 &amp;&amp; 的右侧可见, 可以直接继续参与条件判断
     */
    @Test
    public void testInstanceofPatternWithAnd() {
        Object obj = "Pattern Matching";

        // 模式变量 s 在 && 右侧可见, 直接参与后续条件判断
        if (obj instanceof String s && s.length() > 5) {
            System.out.println("字符串长度大于 5: " + s);
        }

        // 多个条件组合: 类型匹配 + 内容判断
        Object number = 42;
        if (number instanceof Integer i && i > 0 && i < 100) {
            System.out.println("0 到 100 之间的整数: " + i);
        }

        // 复杂条件组合
        Object value = "JDK17";
        if (value instanceof String s && s.startsWith("JDK") && s.length() == 5) {
            System.out.println("以 JDK 开头且长度为 5 的字符串: " + s);
        }
    }

    /**
     * 测试 instanceof 模式匹配在 if-else 中的应用(STANDARD)
     * 模式匹配简化了多分支的类型判断和转换
     */
    @Test
    public void testInstanceofPatternWithIfElse() {
        // 测试不同类型的处理
        printTypeInfo("Hello World");
        printTypeInfo(100);
        printTypeInfo(3.14);
        printTypeInfo(List.of("a", "b", "c"));
    }

    /**
     * 使用 instanceof 模式匹配处理不同类型
     * 每个分支中直接使用模式变量, 无需转型
     */
    private void printTypeInfo(Object obj) {
        if (obj instanceof String s) {
            System.out.println("字符串(长度=" + s.length() + "): " + s);
        } else if (obj instanceof Integer i) {
            System.out.println("整数(十六进制=" + Integer.toHexString(i) + "): " + i);
        } else if (obj instanceof Double d) {
            System.out.println("浮点数(四舍五入=" + Math.round(d) + "): " + d);
        } else if (obj instanceof List<?> list) {
            System.out.println("列表(大小=" + list.size() + "): " + list);
        } else {
            System.out.println("未知类型: " + obj.getClass().getSimpleName());
        }
    }

    /**
     * 测试 instanceof 模式匹配在 else 分支中的应用(STANDARD)
     * 模式变量在 ! 表达式取反后的 else 分支中可见
     */
    @Test
    public void testInstanceofPatternWithElseBranch() {
        Object obj = 12345;

        // !(obj instanceof String s) 取反后, 模式变量 s 在 else 分支中可见
        if (!(obj instanceof String s)) {
            System.out.println("obj 不是字符串, 类型为: " + obj.getClass().getSimpleName());
        } else {
            System.out.println("obj 是字符串, 长度为 " + s.length());
        }

        // 更复杂的 else if 链
        Object value = "Java";
        if (!(value instanceof Integer i)) {
            System.out.println("value 不是整数, 类型为: " + value.getClass().getSimpleName());
        } else {
            System.out.println("value 是整数: " + i);
        }
    }

    /**
     * 测试 instanceof 模式匹配与 equals 方法结合(STANDARD)
     * 在重写 equals 时使用 instanceof 模式匹配简化代码
     */
    @Test
    public void testInstanceofPatternWithEquals() {
        // 演示在 equals 场景中的使用效果
        Object obj1 = "JDK17";
        Object obj2 = "JDK17";
        Object obj3 = "Java";

        // 使用模式匹配进行类型判断和值比较
        if (obj1 instanceof String s1 && obj2 instanceof String s2) {
            System.out.println("两个字符串相等: " + s1.equals(s2));
        }

        if (obj1 instanceof String s1 && obj3 instanceof String s3) {
            System.out.println("两个字符串相等: " + s1.equals(s3));
        }
    }

    /**
     * 测试 instanceof 模式匹配的变量作用域(STANDARD)
     * 模式变量在 if 语句块结束后不再可见, 但可以在条件表达式中嵌套使用
     */
    @Test
    public void testInstanceofPatternVariableScope() {
        Object obj = "Scope Test";

        // 模式变量 s 的作用域在 if 块内
        if (obj instanceof String s && s.contains("Scope")) {
            System.out.println("模式变量 s 在 && 右侧可见: " + s.toUpperCase());
        }

        // 在 if 块外不能使用模式变量(编译错误)
        // System.out.println(s);  // 编译错误: 找不到符号 s

        // 嵌套使用模式匹配
        Object outer = "Outer";
        Object inner = 42;

        if (outer instanceof String s) {
            System.out.println("外层模式变量 s: " + s);
            if (inner instanceof Integer i) {
                System.out.println("内层模式变量 i: " + i);
                System.out.println("内层中可以访问外层模式变量: " + s + " - " + i);
            }
        }
    }
}