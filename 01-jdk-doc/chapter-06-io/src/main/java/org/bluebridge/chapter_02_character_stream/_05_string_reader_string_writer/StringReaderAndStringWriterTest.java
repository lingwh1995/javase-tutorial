package org.bluebridge.chapter_02_character_stream._05_string_reader_string_writer;

import java.io.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc StringReader和StringWriter综合应用
 * @date 2025/9/8 18:36
 */
@Slf4j
public class StringReaderAndStringWriterTest {

    /**
     * 文本处理管道
     */
    @Test
    public void testStringReaderAndStringWriter() {
        String inputText = "Line 1: Hello World\n" +
                "Line 2: Java Programming\n" +
                "Line 3: String Reader Writer";

        try {
            // 使用 StringReader 读取输入
            StringReader stringReader = new StringReader(inputText);
            BufferedReader bufferedReader = new BufferedReader(stringReader);

            // 使用 StringWriter 作为输出缓冲区
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);

            // 处理每一行：转换为大写并添加行号
            String line;
            int lineNumber = 1;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println("Line " + lineNumber + ": " + line.toUpperCase());
                lineNumber++;
            }

            // 输出处理结果
            String result = stringWriter.toString();
            log.info("处理后的文本: {}", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
