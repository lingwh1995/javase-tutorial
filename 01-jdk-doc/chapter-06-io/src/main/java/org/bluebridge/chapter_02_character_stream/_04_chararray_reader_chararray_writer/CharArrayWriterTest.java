package org.bluebridge.chapter_02_character_stream._04_chararray_reader_chararray_writer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.CharArrayWriter;
import java.io.IOException;

/**
 * @author lingwh
 * @desc CharArrayWriter 是 Writer 的子类，用于将数据写入字符数组
 * @date 2025/9/8 17:55
 */
@Slf4j
public class CharArrayWriterTest {

    /**
     * 将数据写入字符数组
     *  类似于
     *      String str = "Hello World";
     *      char[] chars = str.toCharArray();
     *
     *  注意：可以每次写入一个字节，也可以每次写入一个字节数
     */
    @Test
    public void testCharArrayWriter() {
        // 创建 CharArrayWriter
        try (CharArrayWriter writer = new CharArrayWriter()) {
            // 写入字符串
            writer.write("Hello");
            writer.write(' ');
            writer.write("World");

            // 获取字符数组
            char[] chars = writer.toCharArray();
            log.info("字符数组： {}", new String(chars));

            // 转换为字符串
            log.info("字符串： {}", writer.toString());

            // 重置并写入新内容
            writer.reset();
            writer.write("CharArrayWriter...");
            log.info("重置后： {}", writer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
