package org.bluebridge.chapter_02_character_stream._01_input_stream_reader_output_stream_writer._02_file_reader_file_writer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;

/**
 * 文件字符输入流（只能用于读取文件，使用系统默认字符编码，无法指定编码格式）
 *
 * @author lingwh
 * @date 2025/8/21 17:56
 */
@Slf4j
public class FileReaderTest {

    @Test
    public void testInputStreamReader() {
        try (FileReader fr = new FileReader("d:/io/file_reader.txt")) {
            // 10 + 2 = 每一行字节个数 + 2字节换行符 = 12
            char[] buffer = new char[12];
            int length = 0;
            while ((length = fr.read(buffer)) != -1) {
                log.info("本次读取到的长度：{}，读取到的内容： {}", length, new String(buffer, 0, length));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
