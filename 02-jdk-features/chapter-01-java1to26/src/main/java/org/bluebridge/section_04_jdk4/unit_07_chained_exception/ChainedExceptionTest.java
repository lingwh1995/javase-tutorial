package org.bluebridge.section_04_jdk4.unit_07_chained_exception;

import org.junit.Test;

/**
 * JDK 1.4 链式异常（Chained Exceptions）测试
 *
 * 演化历程：
 *   JDK 1.4 引入了链式异常机制，允许将底层异常包装为高层异常的 cause（原因），
 *   从而保留完整的异常堆栈信息，方便问题追溯和排查。
 *
 * 核心 API：
 *   - Throwable.initCause(Throwable)  ：为异常设置原因（仅能调用一次）
 *   - Throwable.getCause()            ：获取异常的根本原因
 *   - Throwable(Throwable cause)      ：通过构造方法直接关联原因
 *   - Throwable(String message, Throwable cause)：同时指定消息和原因
 *
 * @author lingwh
 * @date 2026/08/06 18:19
 */
public class ChainedExceptionTest {

    /**
     * 测试异常链的基本用法：使用构造方法传递 cause
     * 演示高层异常包装底层异常，保留完整异常链
     */
    @Test
    public void testBasicChainedException() {
        try {
            // 模拟底层抛出异常
            throwServiceLayer();
        } catch (ServiceException e) {
            System.out.println("捕获到业务层异常：" + e.getMessage());
            System.out.println("异常类型：" + e.getClass().getSimpleName());
            // 获取 cause（根本原因）
            Throwable cause = e.getCause();
            System.out.println("根本原因异常：" + cause.getClass().getSimpleName());
            System.out.println("根本原因信息：" + cause.getMessage());
            // 打印完整异常链
            System.out.println("完整异常链：");
            Throwable current = e;
            while (current != null) {
                System.out.println("  ├─ " + current.getClass().getSimpleName() + ": " + current.getMessage());
                current = current.getCause();
            }
        }
    }

    /**
     * 测试 initCause() 方法：在异常创建后设置 cause
     * 适用于无法通过构造方法传递 cause 的场景
     */
    @Test
    public void testInitCause() {
        try {
            methodWithInitCause();
        } catch (Exception e) {
            System.out.println("捕获到异常：" + e.getMessage());
            // 通过 initCause 设置的 cause
            Throwable cause = e.getCause();
            System.out.println("通过 initCause 设置的 cause：" + cause.getClass().getSimpleName());
            System.out.println("cause 信息：" + cause.getMessage());
        }
    }

    /**
     * 测试多层异常链：三层嵌套的异常传递
     */
    @Test
    public void testMultiLayerChainedException() {
        try {
            topLevelMethod();
        } catch (TopLevelException e) {
            System.out.println("=== 三层异常链追溯 ===");
            Throwable current = e;
            int level = 1;
            while (current != null) {
                System.out.println("第 " + level + " 层：" + current.getClass().getSimpleName()
                        + " -> " + current.getMessage());
                current = current.getCause();
                level++;
            }
            System.out.println("异常链总层数：" + (level - 1));
        }
    }

    /**
     * 测试异常链的堆栈信息保留
     * 验证 cause 的堆栈信息不会被包装异常覆盖
     */
    @Test
    public void testStackTracePreservation() {
        try {
            throwWrappedException();
        } catch (Exception e) {
            System.out.println("包装异常堆栈（精简）：");
            StackTraceElement[] topStackTrace = e.getStackTrace();
            for (int i = 0; i < Math.min(3, topStackTrace.length); i++) {
                System.out.println("  at " + topStackTrace[i].getMethodName()
                        + "(" + topStackTrace[i].getFileName()
                        + ":" + topStackTrace[i].getLineNumber() + ")");
            }
            System.out.println("  ...");

            // 查看 cause 的堆栈
            Throwable cause = e.getCause();
            System.out.println("根本原因异常堆栈（精简）：");
            StackTraceElement[] causeStackTrace = cause.getStackTrace();
            for (int i = 0; i < Math.min(3, causeStackTrace.length); i++) {
                System.out.println("  at " + causeStackTrace[i].getMethodName()
                        + "(" + causeStackTrace[i].getFileName()
                        + ":" + causeStackTrace[i].getLineNumber() + ")");
            }
            System.out.println("  ...");

            System.out.println("验证结果：根本原因的堆栈信息完整保留，未被包装异常覆盖");
        }
    }

    /**
     * 测试 initCause 重复调用异常
     * initCause 只能被调用一次，重复调用会抛出 IllegalStateException
     */
    @Test
    public void testInitCauseDuplicateCall() {
        try {
            Exception exception = new Exception("原始异常");
            exception.initCause(new RuntimeException("原因 A"));
            // 第二次调用 initCause 会抛出 IllegalStateException
            exception.initCause(new RuntimeException("原因 B"));
            System.out.println("不应执行到这里");
        } catch (IllegalStateException e) {
            System.out.println("捕获到 IllegalStateException：initCause 不能重复调用");
            System.out.println("错误信息：" + e.getMessage());
        }
    }

    /**
     * 测试没有 cause 的异常
     * getCause() 在没有 cause 时返回 null
     */
    @Test
    public void testExceptionWithoutCause() {
        Exception exception = new Exception("没有 cause 的异常");
        Throwable cause = exception.getCause();
        System.out.println("未设置 cause 的异常，getCause() 返回：" + cause);
        assert cause == null : "没有 cause 时 getCause() 应返回 null";
    }

    /**
     * 模拟业务层：调用数据访问层
     */
    private void throwServiceLayer() throws ServiceException {
        try {
            throwDataAccessLayer();
        } catch (DataAccessException e) {
            // 将底层异常包装为高层异常，通过构造方法传递 cause
            throw new ServiceException("业务层处理失败：数据库操作异常", e);
        }
    }

    /**
     * 模拟数据访问层：抛出数据访问异常
     */
    private void throwDataAccessLayer() throws DataAccessException {
        throw new DataAccessException("数据库连接超时：无法访问 192.168.1.100:3306");
    }

    /**
     * 演示 initCause 的使用场景
     */
    private void methodWithInitCause() throws Exception {
        try {
            // 模拟底层异常
            throw new NumberFormatException("无效的数字格式：abc");
        } catch (NumberFormatException e) {
            // 创建异常后通过 initCause 设置原因
            Exception wrapper = new Exception("数据转换失败");
            wrapper.initCause(e);
            throw wrapper;
        }
    }

    /**
     * 顶层方法：模拟最外层调用
     */
    private void topLevelMethod() throws TopLevelException {
        try {
            middleLevelMethod();
        } catch (MiddleLevelException e) {
            throw new TopLevelException("顶层方法执行失败", e);
        }
    }

    /**
     * 中间层方法
     */
    private void middleLevelMethod() throws MiddleLevelException {
        try {
            bottomLevelMethod();
        } catch (BottomLevelException e) {
            throw new MiddleLevelException("中间层处理异常", e);
        }
    }

    /**
     * 底层方法：抛出最原始的异常
     */
    private void bottomLevelMethod() throws BottomLevelException {
        throw new BottomLevelException("底层 IO 错误：文件未找到");
    }

    /**
     * 演示包装异常时堆栈信息保留
     */
    private void throwWrappedException() throws Exception {
        try {
            // 模拟深层错误
            String[] arr = new String[3];
            // 这里会抛出 ArrayIndexOutOfBoundsException
            throw new ArrayIndexOutOfBoundsException("数组索引越界：index=5, length=3");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("包装异常：数组操作失败", e);
        }
    }

    // ==================== 自定义异常类 ====================

    /**
     * 业务层异常
     */
    private static class ServiceException extends Exception {
        public ServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * 数据访问层异常
     */
    private static class DataAccessException extends Exception {
        public DataAccessException(String message) {
            super(message);
        }
    }

    /**
     * 顶层异常
     */
    private static class TopLevelException extends Exception {
        public TopLevelException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * 中间层异常
     */
    private static class MiddleLevelException extends Exception {
        public MiddleLevelException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * 底层异常
     */
    private static class BottomLevelException extends Exception {
        public BottomLevelException(String message) {
            super(message);
        }
    }
}