package org.bluebridge.chapter_01_byte_stream._01_file_input_stream_file_output_stream;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;

/**
 * @author lingwh
 * @desc FileInputStream 字节输入流
 * @date 2025/8/14 17:24
 */
@Slf4j
public class FileInputStreamTest {

    /**
     * 从输入流读取下一个字节的数据。值 byte 作为 int 返回，范围为 0 到 255。如果由于已到达流的末尾而没有可用的字节，则返回值 -1。
     *
     * public abstract int read() throws IOException;
     */
    @Test
    public void testFileInputStreamReadByByte() {
        try (InputStream fis = new FileInputStream("d:/io/input_byte_stream_to_character_stream.txt")){
            /*
            int firstReadResult = fileInputStream.read();
            log.info("第一次读取结果 整型值：{}，字符型值: {}", firstReadResult, (char)firstReadResult);
            int secondReadResult = fileInputStream.read();
            log.info("第二次读取结果 整型值：{}，字符型值: {}", secondReadResult, (char)secondReadResult);
            */
            StringBuilder builder = new StringBuilder();
            int result = 0;
            while((result = fis.read()) != -1){
                builder.append((char)result);
            }
            log.info("builder：{}", builder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从输入流中读取一定数量的字节，并将它们存储到缓冲区数组 b 中。实际读取的字节数以整数形式返回。如果由于已到达流的末尾而没有可用的字节，则返回值 -1。
     *
     * public int read(byte[] b) throws IOException;
     * @param_ b 读取数据的缓冲区。
     */
    @Test
    public void testFileInputStreamReadByBytes() {
        try (InputStream fis = new FileInputStream("d:/io/input_byte_stream_to_character_stream.txt")){
            /*
            byte[] bytes = new byte[2];
            int firstReadResult = fileInputStream.read(bytes);
            log.info("第一次读取结果 长度：{}，值: {}", firstReadResult, new String(bytes,0,firstReadResult));
            int secondReadResult = fileInputStream.read(bytes);
            log.info("第二次读取结果 长度：{}，值: {}", firstReadResult, new String(bytes,0,firstReadResult));
            */
            byte[] buffer = new byte[4];
            int length = 0;
            StringBuilder builder = new StringBuilder();
            while((length = fis.read(buffer)) != -1){
                builder.append(new String(buffer,0,length));
            }
            log.info("builder：{}", builder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
