package org.bluebridge.java8.section_02_lambda;

import org.junit.Test;

/**
 * lambda表达式的延迟执行特性
 *
 * @author lingwh
 * @date 2025/12/2 17:34
 */
public class LambdaLazyExecuteTest {

    /**
     * 测试不使用lambda表达式 - 条件通过时
     */
    @Test
    public void testPrintLog_1() {
        String msgA = "Hello,";
        String msgB = "World,";
        String msgC = "Java!";
        // 条件通过时
        long start = System.currentTimeMillis();
        printLog(1, msgA + msgB + msgC);
        long end = System.currentTimeMillis();
        System.out.println("条件通过时 未使用lambda表达式花费时间:" + (end - start));
    }

    /**
     * 测试不使用lambda表达式 - 条件不通过时
     */
    @Test
    public void testPrintLog_2() {
        String msgA = "Hello,";
        String msgB = "World,";
        String msgC = "Java!";
        // 条件不通过时
        long start = System.currentTimeMillis();
        printLog(2, msgA + msgB + msgC);
        long end = System.currentTimeMillis();
        System.out.println("条件不通过时 未使用lambda表达式花费时间:" + (end - start));
    }

    /**
     * 测试使用lambda表达式 - 条件通过时
     *     可以观察到：执行时间和不使用lambda表达式的耗时基本一致
     */
    @Test
    public void testPrintLogLambda_1() {
        String msgA = "Hello,";
        String msgB = "World,";
        String msgC = "Java!";
        long start = System.currentTimeMillis();
        // 条件通过时
        printLogLambda(1, () -> {
            System.out.println("Lambda执行！");
            return msgA + msgB + msgC;
        });
        long end = System.currentTimeMillis();
        System.out.println("条件通过时 使用lambda表达式花费时间:" + (end - start));
    }

    /**
     * 测试使用lambda表达式 - 条件不通过时
     *     可以观察到：执行时间会大大的缩小代码的执行时间，因为这种写法有的逻辑不会执行
     */
    @Test
    public void testPrintLogLambda_2() {
        String msgA = "Hello,";
        String msgB = "World,";
        String msgC = "Java!";
        // 条件不通过时
        long start = System.currentTimeMillis();
        printLogLambda(2, () -> {
            System.out.println("Lambda执行！");
            return msgA + msgB + msgC;
        });
        long end = System.currentTimeMillis();
        System.out.println("条件通过时 使用lambda表达式花费时间:" + (end - start));
    }

    /**
     * 没有使用lambda表达式的方法
     *
     * @param level
     * @param msg
     */
    private static void printLog(int level, String msg) {
        if (level == 1) {
            System.out.println(msg);
        }
    }

    /**
     * 使用了lambda表达式的方法
     *
     * @param level
     * @param builder
     */
    private void printLogLambda(int level, MessageBuilder builder) {
        if (level == 1) {
            System.out.println(builder.buildMessage());
        }
    }

    /*
     * 下面这个是函数式接口，方便lambda表达式的使用。
     */
    @FunctionalInterface
    interface MessageBuilder {
        String buildMessage();
    }
}
