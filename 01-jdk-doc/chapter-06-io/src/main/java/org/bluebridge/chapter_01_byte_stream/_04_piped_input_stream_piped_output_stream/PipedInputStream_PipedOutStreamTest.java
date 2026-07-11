package org.bluebridge.chapter_01_byte_stream._04_piped_input_stream_piped_output_stream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 管道输入流和管道输出流入门
 * @date 2025/8/16 14:11
 */
@Slf4j
public class PipedInputStream_PipedOutStreamTest {

    @Test
    public void testPipedStream() {
        try {
            // 创建管道输入流
            PipedInputStream pis = new PipedInputStream();
            // 创建管道输出流
            PipedOutputStream pos = new PipedOutputStream();

            // 连接两个流
            pos.connect(pis);

            // 创建写入线程
            Thread writerThread = new Thread(() -> {
                try {
                    String data = "Hello Piped Stream!";
                    pos.write(data.getBytes());
                    log.info("Writer：写入数据 - {}", data);
                    pos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // 创建读取线程
            Thread readerThread = new Thread(() -> {
                try {
                    byte[] buffer = new byte[1024];
                    int bytesRead = pis.read(buffer);
                    String receivedData = new String(buffer, 0, bytesRead);
                    log.info("Reader：读取数据 - {}", receivedData);
                    pis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // 启动线程
            writerThread.start();
            readerThread.start();

            // 等待线程完成
            writerThread.join();
            readerThread.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
