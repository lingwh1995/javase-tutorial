package org.bluebridge.section_04_jdk4.unit_01_assert;

import org.junit.Test;

/**
 * JDK 1.4 引入的 assert 关键字测试
 * 注意：断言默认是禁用的，运行测试时需要添加 -ea 参数启用
 *     例如：java -ea AssertTest
 *
 * @author lingwh
 * @date 2026/08/05 19:01
 */
public class AssertTest {

    /**
     * 测试断言基本语法：assert condition;
     * 当条件为 true 时，断言通过；当条件为 false 时，抛出 AssertionError
     */
    @Test
    public void testAssertBasic() {
        // 断言条件为 true，不会抛出异常
        int value = 10;
        try {
            assert value > 0;
            // 断言通过，正常执行到这里
            System.out.println("断言通过：value > 0");
        } catch (AssertionError e) {
            // 不应进入此分支
            System.out.println("不应执行的 catch 分支");
        }

        // 断言条件为 false，会抛出 AssertionError
        try {
            int negative = -5;
            assert negative > 0;
            // 断言失败，不应执行到这里
            System.out.println("不应执行到这里");
        } catch (AssertionError e) {
            System.out.println("捕获到 AssertionError：" + e.getMessage());
        }
    }

    /**
     * 测试断言带消息语法：assert condition : "message";
     * 断言失败时，会将消息作为 AssertionError 的详细信息
     */
    @Test
    public void testAssertWithMessage() {
        // 断言带消息，断言失败时附带错误描述
        try {
            int age = -1;
            assert age >= 0 : "年龄不能为负数，当前值：" + age;
            System.out.println("不应执行到这里");
        } catch (AssertionError e) {
            System.out.println("捕获到 AssertionError，详细信息：" + e.getMessage());
        }
    }

    /**
     * 测试断言在方法参数校验中的使用场景
     */
    @Test
    public void testAssertInMethodValidation() {
        // 模拟方法参数校验场景
        String name = null;
        try {
            assert name != null : "方法参数 name 不能为 null";
            System.out.println("不应执行到这里");
        } catch (AssertionError e) {
            System.out.println("捕获到 AssertionError：" + e.getMessage());
        }

        // 模拟数值范围校验
        int percentage = 150;
        try {
            assert percentage >= 0 && percentage <= 100 : "百分比超出范围 [0, 100]，当前值：" + percentage;
            System.out.println("不应执行到这里");
        } catch (AssertionError e) {
            System.out.println("捕获到 AssertionError：" + e.getMessage());
        }
    }

    /**
     * 测试断言在流程控制中的使用（作为不可达分支的标记）
     */
    @Test
    public void testAssertInControlFlow() {
        int type = 3;
        try {
            switch (type) {
                case 1:
                    System.out.println("类型 1");
                    break;
                case 2:
                    System.out.println("类型 2");
                    break;
                default:
                    // 使用断言标记理论上不应执行到的分支
                    assert false : "不支持的 type 值：" + type;
                    System.out.println("不应执行到这里");
            }
        } catch (AssertionError e) {
            System.out.println("捕获到 AssertionError：" + e.getMessage());
        }
    }

    /**
     * 演示断言启用/禁用的说明
     * 断言默认是禁用的，需要通过 -ea 或 -enableassertions 启用
     * 在企业开发中，断言通常用于开发测试阶段，不建议用于生产环境
     */
    @Test
    public void testAssertEnableNote() {
        boolean assertEnabled = false;
        // 通过一段会触发断言的代码来检测断言是否启用
        try {
            assert false;
        } catch (AssertionError e) {
            assertEnabled = true;
        }
        System.out.println("断言是否启用：" + (assertEnabled ? "是（已启用 -ea 参数）" : "否（未启用 -ea 参数）"));
        System.out.println("提示：在 IDE 中运行此测试时，请确保已启用断言参数 -ea");
    }
}