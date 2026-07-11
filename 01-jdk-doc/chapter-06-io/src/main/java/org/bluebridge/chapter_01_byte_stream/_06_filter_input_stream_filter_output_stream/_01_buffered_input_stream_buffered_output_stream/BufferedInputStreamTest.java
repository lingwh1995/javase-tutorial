package org.bluebridge.chapter_01_byte_stream._06_filter_input_stream_filter_output_stream._01_buffered_input_stream_buffered_output_stream;

import java.io.*;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

/**
 * @author lingwh
 * @desc BufferedInputStream 带有缓冲区的字节输入流，默认大小是8m，可以通过减少访问磁盘的次数来提高文件读取性能
 * @date 2025/8/16 17:02
 */
@Slf4j
public class BufferedInputStreamTest {
    @Test
    public void testBufferedInputStreamRead() {
        try (InputStream is = new FileInputStream("d:/io/buffered_input_stream.txt");
                InputStream bis = new BufferedInputStream(is)) {
            byte[] buffer = new byte[1024];
            int length = 0;
            StringBuilder builder = new StringBuilder();
            while ((length = bis.read(buffer)) != -1) {
                builder.append(new String(buffer, 0, length));
            }
            log.info("builder.toString()： {}", builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试make()方法和reset() mark后读取多少字节才失效，并不完全由readlimit参数确定，也和BufferedInputStream类的缓冲区 大小有关。
     * 如果BufferedInputStream类的缓冲区大小大于readlimit，在mark以后只有读取超过缓 冲区大小的数据，mark标记才会失效。
     */
    @Test
    public void testBufferedInputStreamMakeAndReset() {
        // 设置缓冲区大小为2个字节
        try (InputStream is = new FileInputStream("d:/io/buffered_input_stream.txt");
                InputStream bis = new BufferedInputStream(is, 2)) {
            log.info("bis.markSupported()：{}", bis.markSupported());
            log.info("(char)bis.read()：{}", (char) bis.read());
            // 设置readlimit为1,调用reset()会报错
            // bufferedInputStream.mark(1);
            // 设置readlimit为5,调用reset()不会报错
            bis.mark(5);
            // 标记位置之后再次读取四个字节
            bis.read(new byte[4], 0, 4);
            bis.reset();
            log.info("(char)bis.read()：{}", (char) bis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
