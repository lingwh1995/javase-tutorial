package org.bluebridge.section_09_jdk9.unit_01_interface;

import org.junit.Test;

/**
 * Java9 接口私有方法测试
 *
 * Java9 允许在接口中定义私有方法, 包括私有实例方法和私有静态方法:
 * 1. 私有实例方法: 只能被接口内部的默认方法和其他私有方法调用
 * 2. 私有静态方法: 只能被接口内部的静态方法、默认方法和其他私有方法调用
 * 3. 私有方法不能被实现类、子接口直接访问, 主要目的是抽取公共逻辑, 提升代码复用性
 *
 * 演化历程: 接口私有方法 JDK 9 STANDARD（JEP 213），无预览历程
 *
 * @author lingwh
 * @date 2026/08/05 18:23
 */
public class InterfacePrivateMethodTest {

    /**
     * 计算器接口: 演示私有实例方法被多个默认方法复用
     */
    interface Calculator {

        /**
         * 默认方法: 计算两个数的和
         */
        default int add(int a, int b) {
            return checkAndCalculate(a, b, '+');
        }

        /**
         * 默认方法: 计算两个数的差
         */
        default int subtract(int a, int b) {
            return checkAndCalculate(a, b, '-');
        }

        /**
         * 接口中的私有实例方法: 校验参数并计算
         */
        private int checkAndCalculate(int a, int b, char operator) {
            System.out.println("私有实例方法校验参数: a = " + a + ", b = " + b);
            return operator == '+' ? a + b : a - b;
        }
    }

    /**
     * 消息服务接口: 演示私有静态方法被多个静态方法复用
     */
    interface MessageService {

        /**
         * 静态方法: 生成欢迎消息
         */
        static String welcome(String name) {
            return buildMessage("欢迎", name);
        }

        /**
         * 静态方法: 生成告别消息
         */
        static String goodbye(String name) {
            return buildMessage("再见", name);
        }

        /**
         * 接口中的私有静态方法: 复用消息拼接逻辑
         */
        private static String buildMessage(String prefix, String name) {
            return prefix + ", " + name + "!";
        }
    }

    /**
     * 测试接口中的私有实例方法: 被多个默认方法复用
     */
    @Test
    public void testPrivateInstanceMethod() {
        // 通过匿名实现类实例化接口
        Calculator calculator = new Calculator() {};
        // 两个默认方法内部都复用了同一个私有实例方法
        System.out.println("add(10, 5) = " + calculator.add(10, 5));
        System.out.println("subtract(10, 5) = " + calculator.subtract(10, 5));
    }

    /**
     * 测试接口中的私有静态方法: 被多个静态方法复用
     */
    @Test
    public void testPrivateStaticMethod() {
        // 通过接口名直接调用静态方法, 内部复用私有静态方法
        System.out.println(MessageService.welcome("张三"));
        System.out.println(MessageService.goodbye("李四"));
    }

    /**
     * 测试私有方法无法在接口外部被调用
     */
    @Test
    public void testPrivateMethodCannotBeCalledOutside() {
        // 私有方法只能在接口内部使用, 在接口外部调用会编译报错, 下面的代码无法通过编译
        // Calculator calculator = new Calculator() {};
        // calculator.checkAndCalculate(1, 2, '+');
        System.out.println("接口私有方法只能在接口内部调用, 外部访问在编译期就会被拒绝");
    }
}
