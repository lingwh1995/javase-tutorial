package org.bluebridge.chapter_02_character_stream._04_chararray_reader_chararray_writer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

/**
 * CharArrayReader和CharArrayWriter综合测试案例
 *
 * @author lingwh
 * @date 2025/9/8 18:15
 */
@Slf4j
public class CharArrayReaderAndCharArrayWriterTest {

    @Test
    public void testCharArrayReaderAndCharArrayWriter() {
        String originalText = "Hello World! This is a test with numbers 123 and symbols @#$.";
        try {
            // 创建读取器和写入器
            CharArrayReader reader = new CharArrayReader(originalText.toCharArray());
            CharArrayWriter writer = new CharArrayWriter();

            // 过滤掉数字和特殊符号，只保留字母和空格
            int ch;
            while ((ch = reader.read()) != -1) {
                char c = (char) ch;
                // 判断字符是否是字母或空格
                if (Character.isLetter(c) || Character.isWhitespace(c)) {
                    writer.write(c);
                }
            }

            String filteredText = writer.toString();
            log.info("原始文本: " + originalText);
            log.info("过滤文本: " + filteredText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
