package org.bluebridge.section_04_jdk4.unit_04_logging;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.*;

/**
 * JDK 1.4 引入的 java.util.logging 包测试
 * 核心组件：Logger、Handler、Formatter、Level
 *
 * @author lingwh
 * @date 2026/08/05 19:02
 */
public class LoggingTest {

    /**
     * 测试 Logger 的基本使用
     * 获取 Logger 实例，输出不同级别日志
     */
    @Test
    public void testLoggerBasic() {
        // 获取 Logger 实例，通常以类全名命名
        Logger logger = Logger.getLogger("org.bluebridge.LoggingTest");

        // 移除默认的 ConsoleHandler 并添加自定义 Handler 以便捕获输出
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);

        StreamHandler handler = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public void publish(LogRecord record) {
                System.out.println(record.getLevel() + ": " + record.getMessage());
                flush();
            }
        };
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);

        // 最常用的方法：直接使用 level 名的方法
        logger.severe("这是一条 SEVERE 级别的日志");
        logger.warning("这是一条 WARNING 级别的日志");
        logger.info("这是一条 INFO 级别的日志");
    }

    /**
     * 测试不同日志级别（SEVERE、WARNING、INFO、CONFIG、FINE、FINER、FINEST）
     * 级别从高到低：SEVERE > WARNING > INFO > CONFIG > FINE > FINER > FINEST
     */
    @Test
    public void testLogLevels() {
        Logger logger = Logger.getLogger("org.bluebridge.LogLevelTest");
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);

        // 自定义 Handler 显示所有级别日志
        Handler handler = new Handler() {
            @Override
            public void publish(LogRecord record) {
                System.out.println(record.getLevel().getName() + " [" + record.getLevel().intValue() + "]: " + record.getMessage());
            }

            @Override
            public void flush() {
            }

            @Override
            public void close() throws SecurityException {
            }
        };
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);

        System.out.println("=== 所有日志级别（从高到低）===");
        // 最高级别
        logger.severe("SEVERE 级别（严重错误）");
        logger.warning("WARNING 级别（警告）");
        logger.info("INFO 级别（信息）");
        logger.config("CONFIG 级别（配置）");
        logger.fine("FINE 级别（详细）");
        logger.finer("FINER 级别（更详细）");
        // 最低级别
        logger.finest("FINEST 级别（最详细）");

        // 使用 log(Level, String) 方法
        logger.log(Level.INFO, "使用 log() 方法输出 INFO 级别日志");
    }

    /**
     * 测试 Handler 和 Formatter
     * Handler：决定日志输出目的地（控制台、文件等）
     * Formatter：决定日志输出格式
     */
    @Test
    public void testHandlerAndFormatter() {
        Logger logger = Logger.getLogger("org.bluebridge.HandlerFormatterTest");
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);

        // 测试 ConsoleHandler（控制台输出）
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);

        // 自定义 Formatter：格式化日志输出
        consoleHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return String.format("[%s] %s: %s%n",
                        record.getLevel().getName(),
                        record.getSourceMethodName(),
                        record.getMessage());
            }
        });
        logger.addHandler(consoleHandler);

        // 测试 StreamHandler 输出到自定义流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        StreamHandler streamHandler = new StreamHandler(baos, new SimpleFormatter());
        streamHandler.setLevel(Level.INFO);
        logger.addHandler(streamHandler);

        logger.info("这条日志会输出到控制台和内存流");
        logger.warning("警告信息");

        // 刷新 StreamHandler 确保内容已写入
        streamHandler.flush();
        System.out.println("内存流中的日志内容：");
        System.out.println(baos.toString());
    }

    /**
     * 测试 Logger 的日志级别过滤
     * 设置 Logger 级别后，低于该级别的日志不会输出
     */
    @Test
    public void testLogLevelFiltering() {
        Logger logger = Logger.getLogger("org.bluebridge.LogFilterTest");
        logger.setUseParentHandlers(false);

        // 自定义 Handler 输出所有收到的日志
        Handler handler = new Handler() {
            @Override
            public void publish(LogRecord record) {
                System.out.println("Handler 收到: " + record.getLevel() + " - " + record.getMessage());
            }

            @Override
            public void flush() {
            }

            @Override
            public void close() throws SecurityException {
            }
        };
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);

        // 设置 Logger 级别为 WARNING，只有 WARNING 及以上级别才会被处理
        logger.setLevel(Level.WARNING);
        System.out.println("=== Logger 级别设置为 WARNING ===");
        logger.fine("FINE 级别日志（不会输出）");
        logger.info("INFO 级别日志（不会输出）");
        logger.warning("WARNING 级别日志（会输出）");
        logger.severe("SEVERE 级别日志（会输出）");

        // 修改 Logger 级别为 FINE
        logger.setLevel(Level.FINE);
        System.out.println("=== Logger 级别设置为 FINE ===");
        logger.fine("FINE 级别日志（现在会输出了）");
        logger.finer("FINER 级别日志（仍不会输出）");
    }

    /**
     * 测试 Logger 的 parent-child 层次关系
     * Logger 之间存在继承关系，子 Logger 的日志会传递给父 Logger
     */
    @Test
    public void testLoggerHierarchy() {
        // 创建父子 Logger
        Logger parentLogger = Logger.getLogger("org.bluebridge");
        Logger childLogger = Logger.getLogger("org.bluebridge.child");

        System.out.println("子 Logger 名称：" + childLogger.getName());
        System.out.println("父 Logger 名称：" + childLogger.getParent().getName());
        System.out.println("子 Logger 是否继承父级别：" + (childLogger.getUseParentHandlers()));
    }

    /**
     * 测试使用 Logger 输出异常信息
     * Logger 支持传入 Throwable 对象
     */
    @Test
    public void testLogException() {
        Logger logger = Logger.getLogger("org.bluebridge.LogExceptionTest");
        logger.setUseParentHandlers(false);

        Handler handler = new Handler() {
            @Override
            public void publish(LogRecord record) {
                System.out.println(record.getLevel() + ": " + record.getMessage());
                if (record.getThrown() != null) {
                    System.out.println("异常信息：" + record.getThrown().getMessage());
                }
            }

            @Override
            public void flush() {
            }

            @Override
            public void close() throws SecurityException {
            }
        };
        logger.addHandler(handler);

        // 记录异常
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.log(Level.SEVERE, "算术运算异常", e);
        }

        // 记录带参数的日志消息
        logger.log(Level.INFO, "用户 {0} 登录系统，IP: {1}", new Object[]{"admin", "192.168.1.1"});
    }
}