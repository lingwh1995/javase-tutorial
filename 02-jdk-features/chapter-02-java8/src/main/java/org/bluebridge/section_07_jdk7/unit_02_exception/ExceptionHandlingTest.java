package org.bluebridge.section_07_jdk7.unit_02_exception;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

/**
 * JDK 7 异常处理改进测试
 *
 * @author lingwh
 * @date 2026/08/05 19:02
 */
public class ExceptionHandlingTest {

    /**
     * 测试 multi-catch：在一个 catch 块中捕获多种异常类型
     */
    @Test
    public void testMultiCatch() {
        try {
            // 模拟可能抛出多种异常的操作
            int choice = 1;
            switch (choice) {
                case 1:
                    throw new IOException("模拟 IO 异常");
                case 2:
                    throw new SQLException("模拟 SQL 异常");
                default:
                    throw new IllegalArgumentException("模拟参数异常");
            }
        } catch (IOException | SQLException e) {
            // multi-catch：使用 | 分隔多个异常类型，变量 e 隐式为 final
            System.out.println("捕获到 IO 或 SQL 异常: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("捕获到参数异常: " + e.getMessage());
        }
    }

    /**
     * 测试更精确的异常重抛（final rethrow）
     * JDK 7 中，catch 块中的异常参数如果是 final 或 effectively final，
     * 编译器会推断抛出的具体异常类型，而不是统一声明为 catch 的类型
     */
    @Test
    public void testPreciseRethrow() throws IOException, SQLException {
        // 调用可能抛出多种异常的方法
        try {
            simulateException(0);
        } catch (Exception e) {
            // JDK 7 之前：这里只能 throws Exception
            // JDK 7 开始：编译器能推断出这里只抛出 IOException 或 SQLException
            throw e;
        }
    }

    /**
     * 测试传统 single-catch 写法（JDK 7 之前的写法）
     */
    @Test
    public void testTraditionalSingleCatch() {
        try {
            int choice = 1;
            switch (choice) {
                case 1:
                    throw new IOException("模拟 IO 异常");
                case 2:
                    throw new SQLException("模拟 SQL 异常");
                default:
                    throw new IllegalArgumentException("模拟参数异常");
            }
        } catch (IOException e) {
            // 传统写法：每种异常需要单独的 catch 块
            System.out.println("捕获到 IO 异常: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("捕获到 SQL 异常: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("捕获到参数异常: " + e.getMessage());
        }
    }

    /**
     * 模拟根据参数抛出不同异常的方法
     *
     * @param flag 控制抛出的异常类型
     * @throws IOException 当 flag 为 0 时抛出
     * @throws SQLException 当 flag 为 1 时抛出
     */
    private void simulateException(int flag) throws IOException, SQLException {
        if (flag == 0) {
            throw new IOException("模拟 IO 异常");
        } else {
            throw new SQLException("模拟 SQL 异常");
        }
    }
}