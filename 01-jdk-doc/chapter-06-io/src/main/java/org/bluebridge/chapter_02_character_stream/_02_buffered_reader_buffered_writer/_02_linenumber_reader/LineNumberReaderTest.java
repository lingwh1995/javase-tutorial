package org.bluebridge.chapter_02_character_stream._02_buffered_reader_buffered_writer._02_linenumber_reader;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * @author lingwh
 * @desc 主要用于读取文本内容并提供行号跟踪功能
 * @date 2025/8/29 11:17
 */

/**
 * LineNumberReader主要特性
 *     行号跟踪：
 *         自动跟踪当前读取的行号
 *         提供 getLineNumber() 方法获取当前行号
 *         行号从 0 开始计数
 *     继承功能：
 *         继承了 BufferedReader 的缓冲功能
 *         支持按行读取文本 (readLine() 方法)
 */
@Slf4j
public class LineNumberReaderTest {

    /**
     * 测试LineNumberReader
     */
    @Test
    public void testLineNumberReader() {
        try (LineNumberReader lnr = new LineNumberReader(new FileReader("d:/io/line_number_reader.txt"))) {
            String line;
            while ((line = lnr.readLine()) != null) {
                log.info("line {}: {}", lnr.getLineNumber(), line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
