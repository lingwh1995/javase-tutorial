package org.bluebridge.chapter_02_character_stream._05_string_reader_string_writer;

import java.io.IOException;
import java.io.StringWriter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc StringWriter 是 Writer 的子类，用于将字符数据写入字符串缓冲区
 * @date 2025/9/8 18:29
 */
@Slf4j
public class StringWriterTest {

    @Test
    public void testStringWriter() {
        try (StringWriter writer = new StringWriter()) {
            // 写入各种数据
            writer.write("Hello");
            writer.write(' ');
            writer.write("World");
            writer.write("!");

            // 获取结果
            String result = writer.toString();
            log.info("写入的内容: ", result);

            // 获取内部缓冲区
            StringBuffer buffer = writer.getBuffer();
            log.info("缓冲区内容: ", buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
