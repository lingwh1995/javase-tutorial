package org.bluebridge.chapter_01_byte_stream._01_file_input_stream_file_output_stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;

/**
 * FileOutputStream 字节输出流
 *
 * @author lingwh
 * @date 2025/8/14 17:28
 */
@Slf4j
public class FileOutputStreamTest {

    /**
     * 将指定的字节写入此文件输出流
     *
     * public void write(int b) throws IOException;
     * @param_ b 要写入的字节
     */
    @Test
    public void testFileOutputStreamWriteByByte() {
        try(FileOutputStream fos = new FileOutputStream("d:/io/file_output_stream.txt")) {
            fos.write(97);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从偏移开始的指定字节数组写入 len 字节到此文件输出流。参数：b – 数据。off – 数据中的起始偏移量。len – 要写入的字节数。
     *
     * public void write(byte[] b, int off, int len) throws IOException;
     * @param_ b 数据
     * @param_ off 数据中的起始偏移量
     * @param_ len 要写入的字节数。
     */
    @Test
    public void testFileOutputStreamWriteByBytes() {
        try(
            FileInputStream fis = new FileInputStream(new File("d:/io/input_byte_stream_to_character_stream.txt"));
            FileOutputStream fos = new FileOutputStream("d:/io/file_output_stream.txt")
        ) {
            byte[] buffer = new byte[2];
            int length = 0;
            while((length = fis.read(buffer)) != -1){
                fos.write(buffer,0,length);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
