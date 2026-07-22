package org.bluebridge.section_01_byte_stream.unit_02_bytearray_input_stream_bytearray_output_stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * ByteArrayOutputStream
 *
 * 它将数据写入内存中的字节数组缓冲区，缓冲区会根据需要自动扩容
 *
 * @author lingwh
 * @date 2025/8/15 18:38
 */
@Slf4j
public class ByteArrayOutputStreamTest {

    @Test
    public void testByteArrayOutputStream() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(12);) {
            baos.write("hello world!".getBytes());
            byte[] data = baos.toByteArray();
            log.info("new String(data)： {}", new String(data));
        }
    }
}
