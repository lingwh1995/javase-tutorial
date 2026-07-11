package org.bluebridge.chapter_02_character_stream._06_piped_reader_piped_writer;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc PipedReader用于从管道中读取字符数据，它是Reader类的子类，通常与PipedWriter配合使用，实现线程间通信。
 * @date 2025/9/12 15:32
 */
@Slf4j
public class PipedReaderAndPipedWriterTest {

    /**
     * 测试PipedReader和PipedWriter
     *
     * @throws IOException
     */
    @Test
    public void testPipedReaderAndPipedWriter() throws IOException {
        // 创建管道读写器
        PipedWriter pipedWriter = new PipedWriter();
        PipedReader pipedReader = new PipedReader();

        // 连接读写器
        pipedReader.connect(pipedWriter);

        // 写入线程
        Thread writerThread = new Thread(() -> {
            try {
                String message = "Hello from writer thread!";
                pipedWriter.write(message);
                pipedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 读取线程
        Thread readerThread = new Thread(() -> {
            try {
                char[] buffer = new char[1024];
                int bytesRead;
                StringBuilder result = new StringBuilder();

                while ((bytesRead = pipedReader.read(buffer)) != -1) {
                    result.append(buffer, 0, bytesRead);
                }

                log.info("Received: {}", result.toString());
                pipedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 启动线程
        readerThread.start();
        writerThread.start();

        try {
            writerThread.join();
            readerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
