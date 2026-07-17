package org.bluebridge.chapter_02_character_stream._05_string_reader_string_writer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

/**
 * StringReader 是 Reader 的子类，用于从字符串中读取字符数据。
 *
 * @author lingwh
 * @date 2025/9/8 18:28
 */
@Slf4j
public class StringReaderTest {

    @Test
    public void testStringReader() {
        String text = "Hello World! This is a test string.";

        try (StringReader reader = new StringReader(text)) {
            // 逐字符读取
            int ch;
            while ((ch = reader.read()) != -1) {
                log.info("读取的字符: {}", (char) ch);
            }
            log.info("-----");

            // 标记和重置示例
            reader.reset(); // 重置到开始位置
            reader.mark(10); // 设置标记

            // 跳过前5个字符
            reader.skip(5);

            // 读取剩余内容
            char[] buffer = new char[100];
            int count = reader.read(buffer);
            log.info("跳过5个字符后的内容： {}", new String(buffer, 0, count));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
