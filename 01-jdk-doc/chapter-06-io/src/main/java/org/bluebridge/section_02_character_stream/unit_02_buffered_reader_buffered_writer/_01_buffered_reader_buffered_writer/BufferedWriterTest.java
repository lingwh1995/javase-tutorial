package org.bluebridge.section_02_character_stream.unit_02_buffered_reader_buffered_writer._01_buffered_reader_buffered_writer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * BufferedWriter
 *
 * 1. BufferedWriter 是带有缓冲区的输出流（通过缓冲字符来提高字符输出效率，特别适合输出大量文本数据）
 * 2. BufferedWriter主要特点
 *      缓冲机制: 内部使用缓冲区减少实际的写入操作次数
 *      行读取支持: 提供高效的按行写入方法
 *      性能优化: 比直接写入字符流更高效
 *      字符流操作: 专门用于处理字符数据而非字节数据
 * 3. 最佳实践
 *      始终关闭资源: 使用 try-with-resources 或确保在 finally 块中关闭 BufferedWriter
 *      合适的缓冲区大小: 根据数据特征选择合适的缓冲区大小，默认通常是8192字符
 *      优先使用 newLine() 方法写入平台相关的行分隔符，而不是在写入时使用手动拼接的换行符，如 "\n" 或 "\r\n"
 *      异常处理: 妥善处理 IOException 异常、
 * 4. BufferedWriter 是处理文本数据时非常重要的类，通过缓冲机制显著提高了文本写入的性能，特别是在处理大量文本数据时效果明显。
 *
 * @author lingwh
 * @date 2025/8/29 9:54
 */
@Slf4j
public class BufferedWriterTest {

    /**
     * 测试BufferedWriter
     */
    @Test
    public void testBufferedWriter() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("buffered_writer.txt"))) {
            bw.write("Hello, BufferedWriter!");
            // 使用此方法写入换行符，而不是使用 "\n" 或 "\r\n"
            bw.newLine();
            bw.write("这是第二行内容");
            // 写入完成后最后调用一下flush()确保数据被写入
            bw.flush();
            log.info("数据写入完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
