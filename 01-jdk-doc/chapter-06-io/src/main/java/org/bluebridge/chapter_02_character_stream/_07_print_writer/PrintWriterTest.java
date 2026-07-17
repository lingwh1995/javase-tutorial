package org.bluebridge.chapter_02_character_stream._07_print_writer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * PrintWriter是Java IO包中的一个类，用于以格式化的方式打印各种数据类型的文本输出。它是 Writer 类的子类，提供了方便的打印方法。
 *
 * @author lingwh
 * @date 2025/9/12 17:02
 */
@Slf4j
public class PrintWriterTest {

    /**
     * 测试PrintWriter输出到控制台
     */
    @Test
    public void testPrintWriterToConsole() {
        // 创建输出到控制台的PrintWriter
        try (PrintWriter pw = new PrintWriter(System.out, true);) {
            // 打印各种数据类型
            pw.println("Hello, World!");
            pw.println("Integer: " + 42);
            pw.println("Double: " + 3.14159);
            pw.println("Boolean: " + true);

            // 格式化输出
            pw.printf("Name: %s, Age: %d, Score: %.2f%n", "Alice", 25, 95.5);
        }
    }

    /**
     * 测试PrintWriter输出到文件
     */
    @Test
    public void testPrintWriterToFile() {
        // 创建输出到文件的PrintWriter
        try (PrintWriter pw = new PrintWriter("d:/io/print_writer.txt", "UTF-8")) {
            // 写入数据
            pw.println("=== 系统日志 ===");
            pw.println("时间: " + new Date());
            pw.println("用户: " + System.getProperty("user.name"));
            pw.println("操作系统: " + System.getProperty("os.name"));

            // 格式化输出
            for (int i = 1; i <= 10; i++) {
                pw.printf("记录 %d: 处理完成%n", i);
            }

            pw.println("=== 日志结束 ===");
            log.info("文件写入完成");
        } catch (IOException e) {
            log.info("文件操作错误: {}", e.getMessage());
        }
    }

    /**
     * 测试PrintWriter输出到字符串
     */
    @Test
    public void testPrintWriterToString() {
        // 使用 StringWriter 作为输出目标
        try (StringWriter stringWriter = new StringWriter();
                PrintWriter pw = new PrintWriter(stringWriter)) {

            // 写入数据
            pw.println("这是第一行");
            pw.println("这是第二行");
            pw.printf("数字: %d, 字符串: %s%n", 123, "测试");

            // 获取结果字符串
            String result = stringWriter.toString();

            log.info("输出内容: {}", result);
        } catch (IOException e) {
            log.info("文件操作错误: {}", e.getMessage());
        }
    }

    /**
     * 测试PrintWriter输出到网络套接字
     * 测试方法： cmd -> telnet 127.0.0.1 8080/telnet localhost 8080 ->
     * 直接输入内容（只能发送单个字符）/按下Ctrl+]后输入 send + 内容（可以发送字符串） -> 查看idea控制台接收到的信息
     */
    @Test
    public void testPrintWriterToSocket() {
        try {
            // 创建服务器套接字
            ServerSocket serverSocket = new ServerSocket(8080);
            log.info("服务器启动，等待连接......");

            // 接受客户端连接
            Socket clientSocket = serverSocket.accept();
            log.info("客户端已连接");

            // 创建PrintWriter发送响应
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // 发送HTTP响应
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html; charset=utf-8");
            out.println();
            out.println("<html><body>");
            out.println("<h1>Hello from PrintWriter Server!</h1>");
            out.println("<p>当前时间: " + new java.util.Date() + "</p>");
            out.println("</body></html>");

            // 关闭资源
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试PrintWriter错误处理
     */
    @Test
    public void testPrintWriterErrorHandling() {
        try {
            // 创建PrintWriter
            PrintWriter pw = new PrintWriter("d:/io/print_writer_error_handling.txt.txt");
            // 带有自动刷新的构造参数
            // PrintWriter pw = new PrintWriter(new
            // FileWriter("d:/io/print_writer_error_handling.txt", "UTF-8"), true)
            // 正常输出
            pw.println("正常输出内容");
            log.info("检查错误状态: {}", pw.checkError());

            // 强制关闭底层流来模拟错误
            pw.close();

            // 尝试在关闭后写入（会产生错误）
            pw.println("这会产生错误");

            // 检查是否有错误
            if (pw.checkError()) {
                log.info("检测到错误");
            }

            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
