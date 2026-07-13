package org.bluebridge.chapter_02_character_stream._04_chararray_reader_chararray_writer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.CharArrayReader;
import java.io.IOException;

/**
 * CharArrayReader 是 Reader 的子类，用于从字符数组中读取字符
 *
 * @author lingwh
 * @date 2025/9/8 17:49
 */
@Slf4j
public class CharArrayReaderTest {

    /**
     * 从字符数组中读取字符
     *  类似于
     *      char[] chars = {'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'};
     *      String str = new String(chars);
     *
     *  注意：可以每次读取一个字节，也可以每次读取一个字节数
     */
    @Test
    public void testCharArrayReader() {
        // 创建字符数组
        char[] chars = {'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'};

        // 使用字符数组创建 CharArrayReader
        try (CharArrayReader reader = new CharArrayReader(chars)) {
            int ch;
            // 逐个读取字符
            while ((ch = reader.read()) != -1) {
                log.info("{}", (char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("---------");

        // 使用部分字符数组创建 CharArrayReader
        try (CharArrayReader reader = new CharArrayReader(chars, 6, 5)) {
            int ch;
            while ((ch = reader.read()) != -1) {
                log.info("{}", (char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
