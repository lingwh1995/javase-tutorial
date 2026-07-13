package org.bluebridge.chapter_01_byte_stream._02_bytearray_input_stream_bytearray_output_stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * ByteArrayInputStream
 *
 * 在内存中创建一个字节数组，将输入流中读取的数据保存到字节数组的缓冲区中，可以使用内存充当缓冲区，从而避免文件IO
 *
 * @author lingwh
 * @date 2025/8/15 15:10
 */
@Slf4j
public class ByteArrayInputStreamTest {

    @Test
    public void testByteArrayInputStream() {
        byte[] bytes = "abcdefghijklmnopqrst".getBytes();
        log.info("总长度： {}", bytes.length);
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
            // avaiable表示剩余可字节数，除了a，还剩19个字节
            if (bais.available() > 0) {
                log.info("本次读取到的内容： {}，剩余可读字节数： {}", (char) bais.read(), bais.available());
            }
            for (int i = 0; i < 3; i++) {
                if (bais.available() > 0) {
                    log.info("本次读取到的内容： {}", (char) bais.read());
                }
            }
            // 现在位置是d，跳过3个字节，下一个字节是h
            long skipWords = bais.skip(3);
            log.info("跳过字节数： {}，本次读取到的内容： {}", skipWords, (char) bais.read());
            if (bais.markSupported()) {
                log.info("support mark：{}", bais.markSupported());
            }
            // 现在是位置在i,进行标记.
            bais.mark(0);
            // 使用字节数组,一次性读取三个字节.
            byte[] byteArray = new byte[3];
            bais.read(byteArray, 0, 3);
            log.info("new String(byteArray)：{}", new String(byteArray));
            // 通过reset()方法将指针指到到mark位置
            bais.reset();
            log.info("reset：{}", (char) bais.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
