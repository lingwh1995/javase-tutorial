package org.bluebridge.section_06_jdk6.unit_03_console;

import org.junit.Test;

import java.io.Console;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

/**
 * JDK 6 java.io.Console 测试
 *
 * java.io.Console 是 JDK 6 引入的用于与命令行控制台交互的类。
 * 提供比 System.in/out 更丰富的功能，如：
 * - readPassword(): 读取密码时禁用回显，提高安全性
 * - readLine(): 读取一行文本，支持格式化提示
 * - format()/printf(): 格式化输出到控制台
 * - reader()/writer(): 获取与 Console 关联的 Reader 和 PrintWriter
 *
 * 注意：Console 只能从命令行中运行的程序获取，在 IDE（如 IntelliJ IDEA、Eclipse）中
 * 运行时 System.console() 返回 null。本测试使用条件判断处理这种情况。
 *
 * @author lingwh
 * @date 2026/08/05 19:06
 */
public class ConsoleTest {

    /**
     * 测试获取 Console 实例
     */
    @Test
    public void testGetConsole() {
        // 通过 System.console() 获取 Console 实例
        Console console = System.console();
        if (console != null) {
            System.out.println("成功获取 Console 实例");
            System.out.println("  控制台字符集: " + console.charset());
            System.out.println("  reader(): " + console.reader());
            System.out.println("  writer(): " + console.writer());
        } else {
            System.out.println("System.console() 返回 null");
            System.out.println("原因: 当前程序不是在命令行终端中运行（如 IDE 中执行）");
            System.out.println("提示: Console 只能从命令行启动的程序中获取");
        }
        System.out.println("--------------------------------------");
        System.out.println("Console 与 System.in/out 的区别: ");
        System.out.println("  1. Console 提供 readPassword() 方法，输入密码时不可见");
        System.out.println("  2. Console 提供格式化输入输出（printf/format/readLine 带格式化参数）");
        System.out.println("  3. Console 与 JVM 关联，在后台 I/O 重定向时不可用");
        System.out.println("  4. Console 的 Reader/Writer 支持字符编码转换");
    }

    /**
     * 测试 Console 的 readLine 方法
     * 注意：如果 Console 为 null，会跳过实际操作
     */
    @Test
    public void testConsoleReadLine() {
        Console console = System.console();
        if (console == null) {
            System.out.println("跳过 readLine 测试：Console 不可用（非命令行环境）");
            System.out.println("演示 Console.readLine() 的用法: ");
            System.out.println("  console.readLine() -> 读取一行文本");
            System.out.println("  console.readLine(String fmt, Object... args) -> 带格式化提示的读取");
            System.out.println("  console.readLine(\"请输入用户名: \") -> 显示提示信息后读取输入");
            return;
        }
        // 使用 readLine() 读取一行文本
        String line = console.readLine();
        System.out.println("读取到的内容: " + line);
        // 使用带格式化参数的 readLine：显示提示信息并读取输入
        String name = console.readLine("请输入您的姓名: ");
        System.out.println("您好, " + name + "!");
        // 使用带多个格式化参数的 readLine
        String info = console.readLine("请输入 [%s] 的年龄: ", name);
        System.out.println(name + " 的年龄是: " + info);
    }

    /**
     * 测试 Console 的 readPassword 方法（密码输入不回显）
     * 注意：如果 Console 为 null，会跳过实际操作
     */
    @Test
    public void testConsoleReadPassword() {
        Console console = System.console();
        if (console == null) {
            System.out.println("跳过 readPassword 测试：Console 不可用（非命令行环境）");
            System.out.println("演示 Console.readPassword() 的用法: ");
            System.out.println("  console.readPassword() -> 读取密码，输入时字符不可见");
            System.out.println("  console.readPassword(\"请输入密码: \") -> 带提示的密码读取");
            System.out.println("  返回 char[] 而非 String，使用后应清空数组以提高安全性");
            return;
        }
        // 使用 readPassword() 读取密码（输入时不会显示任何字符）
        char[] password = console.readPassword("请输入密码: ");
        System.out.println("密码已读取（长度: " + password.length + "）");
        // 使用带格式化参数的 readPassword
        char[] confirmPassword = console.readPassword("请再次输入密码确认: ");
        // 比较两次输入的密码是否一致
        boolean match = Arrays.equals(password, confirmPassword);
        System.out.println("两次密码输入" + (match ? "一致" : "不一致"));
        // 使用完毕后清空密码数组，防止内存中残留敏感信息
        Arrays.fill(password, ' ');
        Arrays.fill(confirmPassword, ' ');
        System.out.println("密码数组已清空");
    }

    /**
     * 测试 Console 的 format 和 printf 方法
     * 注意：如果 Console 为 null，会跳过实际操作
     */
    @Test
    public void testConsoleFormatAndPrintf() {
        Console console = System.console();
        if (console == null) {
            System.out.println("跳过 format/printf 测试：Console 不可用（非命令行环境）");
            System.out.println("演示 Console.format() 和 Console.printf() 的用法: ");
            System.out.println("  console.format(String fmt, Object... args) -> 格式化输出");
            System.out.println("  console.printf(String fmt, Object... args) -> 与 format 功能相同");
            System.out.println("  两者都返回 Console 实例，支持链式调用");
            return;
        }
        // 使用 format() 方法格式化输出
        console.format("========================================%n");
        console.format("  用户信息表%n");
        console.format("========================================%n");
        console.format("  姓名: %-10s | 年龄: %-3d | 成绩: %-5.1f%n", "张三", 25, 95.5);
        console.format("  姓名: %-10s | 年龄: %-3d | 成绩: %-5.1f%n", "李四", 22, 88.0);
        console.format("  姓名: %-10s | 年龄: %-3d | 成绩: %-5.1f%n", "王五", 24, 92.3);
        console.format("========================================%n");
        // 使用 printf() 方法（与 format 行为相同，返回 Console 实例支持链式调用）
        console.printf("printf 方法测试: %s = %d%n", "计数值", 100);
        // 链式调用
        console.printf("第一部分: ").printf("第二部分: ").printf("第三部分%n");
        // 使用 format 输出不同格式的数据
        console.format("十六进制: %1$d = 0x%1$x%n", 255);
        console.format("浮点数: %.2f%n", 3.1415926);
        console.format("科学计数法: %e%n", 12345.6789);
    }

    /**
     * 测试 Console 的 reader 和 writer 方法
     * 注意：如果 Console 为 null，会跳过实际操作
     */
    @Test
    public void testConsoleReaderAndWriter() {
        Console console = System.console();
        if (console == null) {
            System.out.println("跳过 reader/writer 测试：Console 不可用（非命令行环境）");
            System.out.println("演示 Console.reader() 和 Console.writer() 的用法: ");
            System.out.println("  console.reader() -> 获取与 Console 关联的 Reader");
            System.out.println("  console.writer() -> 获取与 Console 关联的 PrintWriter");
            System.out.println("  通过 Reader/Writer 可以实现更灵活的 I/O 操作");
            return;
        }
        // 获取 Console 关联的 PrintWriter 并输出
        console.writer().println("通过 Console.writer() 输出信息");
        console.writer().println("PrintWriter 支持所有输出方法: print, println, printf, format");
        // 使用 flush 确保输出立即显示
        console.writer().flush();
        // 获取 Console 关联的 Reader
        Reader reader = console.reader();
        System.out.println("获取到 Console 关联的 Reader: " + reader.getClass().getName());
        try {
            // 通过 Reader 读取字符数组
            char[] buffer = new char[100];
            System.out.print("请输入一些文字（通过 Reader 读取）: ");
            int charsRead = reader.read(buffer);
            System.out.println("通过 Reader 读取了 " + charsRead + " 个字符");
        } catch (IOException e) {
            System.out.println("通过 Reader 读取时发生异常: " + e.getMessage());
        }
    }

    /**
     * 测试 Console 在非命令行环境中的行为
     */
    @Test
    public void testConsoleAvailability() {
        Console console = System.console();
        System.out.println("Console 可用性测试: ");
        System.out.println("  System.console() != null: " + (console != null));
        System.out.println("--------------------------------------");
        System.out.println("Console 使用场景: ");
        System.out.println("  1. 命令行工具（CLI）的用户交互");
        System.out.println("  2. 需要安全输入密码的场景（如数据库连接、SSH 密钥）");
        System.out.println("  3. 需要格式化控制台输出的应用");
        System.out.println("--------------------------------------");
        System.out.println("替代方案（当 Console 不可用时）: ");
        System.out.println("  1. 使用 System.in 和 Scanner 替代 readLine");
        System.out.println("  2. 使用 System.out.printf 替代 console.format");
        System.out.println("  3. 密码输入可使用 System.console() 回退到 System.in");
        System.out.println("  4. 推荐使用 JLine 等第三方库提供更丰富的控制台交互");
    }
}