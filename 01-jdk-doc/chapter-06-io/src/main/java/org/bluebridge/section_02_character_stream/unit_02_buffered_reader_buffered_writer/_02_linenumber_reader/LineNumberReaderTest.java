package org.bluebridge.section_02_character_stream.unit_02_buffered_reader_buffered_writer._02_linenumber_reader;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * 主要用于读取文本内容并提供行号跟踪功能
 *
 * @author lingwh
 * @date 2025/8/29 11:17
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
