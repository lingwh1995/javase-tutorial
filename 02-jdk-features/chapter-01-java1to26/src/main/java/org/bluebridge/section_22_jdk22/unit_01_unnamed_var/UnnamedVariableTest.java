package org.bluebridge.section_22_jdk22.unit_01_unnamed_var;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.BiFunction;

/**
 * JDK 22 未命名变量和模式测试(STANDARD 正式特性)
 *
 * 未命名变量和模式(Unnamed Variables & Patterns, JEP 456) 是 JDK 22 的 STANDARD 正式特性,
 * 使用下划线 _ 表示未命名变量和模式, 在不需要使用变量的场合替代原有变量名。
 *
 * 使用场景:
 *   1. 未命名变量: 在声明时使用 _ 表示不需要该变量的值
 *   2. try-with-resources 中使用 _ 表示不需要资源变量
 *   3. lambda 中使用 _ 表示不需要的参数
 *   4. 异常处理 catch 中使用 _ 表示不需要异常变量
 *   5. for 循环中使用 _ 表示不需要循环变量
 *
 * 演化历程: 未命名变量 JDK 21(JEP 443, 1st PREVIEW) → JDK 22(JEP 456, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 09:10
 */
public class UnnamedVariableTest {

    /**
     * 测试未命名变量在赋值语句中的使用(STANDARD)
     * 使用 _ 声明未命名变量, 表示不需要该变量的值
     */
    @Test
    public void testUnnamedVariableAssignment() {
        // ===== 旧版实现方式(JDK 22 之前): 即使不需要变量的值, 也必须给变量起名 =====
        // int unused = 42;   // 变量名 unused 占用命名空间, 且 IDE 会提示未使用
        // ===== 新版实现方式(JDK 22 起): 使用 _ 表示未命名变量, 无需起名 =====
        // 使用 _ 作为未命名变量, 忽略不需要的值
        int _ = 42;
        System.out.println("未命名变量 _ 已赋值, 但无法直接引用");

        // 多个未命名变量
        int _ = 100;
        String _ = "hello";
        System.out.println("多个未命名变量声明完成");

        // 在解构赋值中忽略不需要的值
        List<String> list = List.of("A", "B", "C");
        System.out.println("列表大小: " + list.size());
        System.out.println("--------------------------------------");
    }

    /**
     * 测试未命名变量在 try-with-resources 中的使用(STANDARD)
     * 当不需要使用资源变量时, 使用 _ 替代
     */
    @Test
    public void testUnnamedVariableInTryWithResources() {
        // 使用 _ 作为未命名资源变量
        // 注意: 这里假设有文件路径, 实际运行时可能抛出异常
        // 编译时使用 _ 语法, 表示不需要资源变量
        String filePath = "test.txt";
        System.out.println("try-with-resources 中使用 _ 作为未命名变量");
        System.out.println("资源路径: " + filePath);

        // 展示传统方式和使用 _ 的对比
        // 传统方式: try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        // JDK 22 方式: try (BufferedReader _ = new BufferedReader(new FileReader(filePath)))
        // 注意: 实际运行时需要文件存在, 这里仅演示语法
        System.out.println("在 JDK 22 中, 可以写成:");
        System.out.println("try (BufferedReader _ = new BufferedReader(new FileReader(path))) { ... }");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试未命名变量在 lambda 中的使用(STANDARD)
     * 在 lambda 表达式中使用 _ 表示不需要的参数
     */
    @Test
    public void testUnnamedVariableInLambda() {
        // ===== 旧版实现方式(JDK 22 之前): 不使用的参数也必须起名, 例如 (a, b) -> a =====
        // BiFunction<Integer, Integer, Integer> add = (a, b) -> a;
        // ===== 新版实现方式(JDK 22 起): lambda 参数使用 _ 表示不需要该参数 =====
        // 使用 _ 作为未命名 lambda 参数
        BiFunction<Integer, Integer, Integer> add = (a, _) -> a;
        System.out.println("lambda 使用 _ 忽略第二个参数: " + add.apply(10, 20));

        // 多个未命名参数
        BiFunction<Integer, Integer, Integer> first = (a, _) -> a;
        System.out.println("获取第一个参数: " + first.apply(100, 999));

        // 在流操作中使用未命名变量
        List<String> list = List.of("A", "B", "C", "D", "E");
        long count = list.stream()
                .filter(_ -> true)
                .count();
        System.out.println("过滤后数量: " + count);
        System.out.println("--------------------------------------");
    }

    /**
     * 测试未命名变量在异常处理 catch 中的使用(STANDARD)
     * 在 catch 块中使用 _ 表示不需要捕获的异常变量
     */
    @Test
    public void testUnnamedVariableInCatch() {
        // 使用 _ 作为未命名异常变量
        try {
            int result = 10 / 0;
            System.out.println("计算结果: " + result);
        // ===== 旧版实现方式(JDK 22 之前): catch 中即使不用异常也要给异常变量起名 =====
        // } catch (ArithmeticException e) {
        //     System.out.println("捕获到算术异常");
        // }
        // ===== 新版实现方式(JDK 22 起): catch 中使用 _ 省略异常变量 =====
        } catch (ArithmeticException _) {
            // 不需要使用异常变量, 使用 _ 替代
            System.out.println("捕获到算术异常, 使用 _ 忽略异常变量");
        }

        // 多个 catch 块中使用 _
        try {
            String str = null;
            str.length();
        } catch (NullPointerException _) {
            System.out.println("捕获到空指针异常, 忽略异常变量");
        }

        System.out.println("--------------------------------------");
    }

    /**
     * 测试未命名变量在 for 循环中的使用(STANDARD)
     * 在循环中使用 _ 表示不需要循环变量
     */
    @Test
    public void testUnnamedVariableInLoop() {
        // ===== 旧版实现方式(JDK 22 之前): 循环变量必须命名, 即使不使用 =====
        // for (String item : list) { ... }
        // ===== 新版实现方式(JDK 22 起): 增强 for 循环使用 _ 忽略元素 =====
        // 注意: 传统 for (int i = 0; i < n; i++) 循环中不能使用 _,
        // 因为 _ 是未命名变量, 无法在自增/比较表达式中被引用
        // 增强 for 循环中使用 _
        List<String> list = List.of("A", "B", "C");
        for (String _ : list) {
            System.out.println("遍历元素, 使用 _ 忽略具体值");
        }
    }

    /**
     * 测试未命名变量在 switch 表达式中的使用(STANDARD)
     * 在 switch 中使用未命名模式匹配但不绑定值
     */
    @Test
    public void testUnnamedVariableInSwitch() {
        Object obj = "Hello, JDK 22!";

        // ===== 旧版实现方式(JDK 22 之前): 类型模式必须绑定变量名, 即使不使用 =====
        // case Integer i -> "这是一个整数, 忽略具体值";
        // ===== 新版实现方式(JDK 22 起): 未命名模式使用 _ 表示不需要绑定 =====
        // 使用 switch 模式匹配, 用 _ 忽略不需要的绑定
        String result = switch (obj) {
            case Integer _ -> "这是一个整数, 忽略具体值";
            case String _ -> "这是一个字符串, 忽略具体值";
            case null -> "null 值";
            default -> "其他类型";
        };
        System.out.println("switch 匹配结果: " + result);

        // 多个 case 组合
        Object number = 42;
        String typeInfo = switch (number) {
            case Integer _, Long _, Double _ -> "这是一个数字类型";
            case String _ -> "这是一个字符串类型";
            case null -> "null 值";
            default -> "其他类型: " + number.getClass().getSimpleName();
        };
        System.out.println("类型信息: " + typeInfo);
    }
}