package org.bluebridge.chapter_01_byte_stream._02_bytearray_input_stream_bytearray_output_stream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc ByteArrayOutputStream 它将数据写入内存中的字节数组缓冲区，缓冲区会根据需要自动扩容
 * @date 2025/8/15 18:38
 */
@Slf4j
public class ByteArrayOutputStreamTest {

    @Test
    public void testByteArrayOutputStream() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(12); ) {
            baos.write("hello world!".getBytes());
            byte[] data = baos.toByteArray();
            log.info("new String(data)： {}", new String(data));
        }
    }
}
