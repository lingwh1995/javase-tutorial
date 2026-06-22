package org.bluebridge.chapter_01_byte_stream._06_filter_input_stream_filter_output_stream._03_pushback_input_stream;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * @author lingwh
 * @desc PushbackInputStream 允许将读取的字节"推回"到流中，使得这些字节可以被再次读取。这种功能在需要"预览"或"回退"输入流中的数据时非
 *                           常有用，特别是在解析器和编译器等场景中。该类提供了一种机制，可以"偷窥"来自输入流的内容而不对它们进行破坏。
 * @date 2025/8/16 17:41
 */
@Slf4j
public class PushbackInputStreamTest {

    @Test
    public void testPushbackInputStream() {
        // 创建字符串输入流
        String data = "Hello World!";
        try(ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes());
            PushbackInputStream pbis = new PushbackInputStream(bais)){
            // 读取前5个字符
            log.info("读取的字符：");
            for (int i = 0; i < 3; i++) {
                int ch = pbis.read();
                log.info("(char) ch： {}", (char) ch);
            }

            // 推回一个字符 ('o')
            pbis.unread('o');
            log.info("推回字符: o");

            // 再次读取，应该读到推回的字符
            int ch = pbis.read();
            log.info("再次读取： {}", (char) ch);

            // 继续读取剩余字符
            log.info("剩余字符：");
            while ((ch = pbis.read()) != -1) {
                log.info("{}", (char) ch);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
