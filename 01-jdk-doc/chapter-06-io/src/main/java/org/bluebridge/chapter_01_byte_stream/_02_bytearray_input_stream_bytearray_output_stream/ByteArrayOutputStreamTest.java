package org.bluebridge.chapter_01_byte_stream._02_bytearray_input_stream_bytearray_output_stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author lingwh
 * @desc ByteArrayOutputStream 它将数据写入内存中的字节数组缓冲区，缓冲区会根据需要自动扩容
 * @date 2025/8/15 18:38
 *
 * 主要特点
 *    内存操作：数据存储在内存中的字节数组中，不涉及文件I/O操作
 *    自动扩容：当缓冲区满时会自动增加容量
 *    线程安全：该类的方法是同步的，可以在多线程环境中使用
 *
 * 使用场景
 *    临时存储字节数据
 *    将多个数据源的数据合并
 *    在网络传输中作为缓冲区
 *    单元测试中模拟输出流
 */
@Slf4j
public class ByteArrayOutputStreamTest {

    @Test
    public void testByteArrayOutputStream() throws IOException {
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream(12);){
            baos.write("hello world!".getBytes());
            byte[] data = baos.toByteArray();
            log.info("new String(data)： {}",new String(data));
        }
    }

}
