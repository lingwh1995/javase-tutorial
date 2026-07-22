package org.bluebridge.section_01_byte_stream.unit_06_filter_input_stream_filter_output_stream._04_print_stream;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 用于格式化输出数据
 *
 * @author lingwh
 * @date 2025/8/20 18:24
 */
public class PrintStreamTest {

    /**
     * 测试PrintStream类的使用，当内容输出到文件中时会自动换行
     * tips: 和c语言中的sprintf()函数类似，sprintf()是格式化输出到字符串，PrintStream是格式化输出到文件
     */
    @Test
    public void testPrintStream() {
        try {
            // 创建FileOutputStream对象，用于写入文件
            FileOutputStream fileOutputStream = new FileOutputStream("d:/io/print_stream.txt");
            // 创建PrintStream对象，指定autoFlush为true，以便自动刷新输出缓冲区
            PrintStream printStream = new PrintStream(fileOutputStream, true);
            // 使用print方法输出字符串
            printStream.print("Hello, ");
            // 使用println方法输出字符串并换行
            printStream.println("World!");
            // 使用printf方法格式化输出
            printStream.printf("整数：%d，小数：%f，字符串：%s%n", 10, 3.14, "Java");
            // 写入单个字节，写入ASCII码为65的字符，即'A'
            printStream.write(65);
            // 关闭PrintStream
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
