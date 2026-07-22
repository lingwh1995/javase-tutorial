package org.bluebridge.section_01_byte_stream.unit_04_piped_input_stream_piped_output_stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.util.Random;

/**
 * 使用管道输入流和管道输出流实现生产者消费者模型
 *
 * @author lingwh
 * @date 2025/8/16 14:40
 */
@Slf4j
public class PipedInputStream_PipedOutStream_ProducerConsumerTest {

    @Test
    public void testPipedStreamProducerConsumer() {
        try {
            // 创建管道输入流
            PipedInputStream pis = new PipedInputStream();
            // 创建管道输出流
            PipedOutputStream pos = new PipedOutputStream();

            // 连接两个流
            pos.connect(pis);

            Thread producerThread = new Thread(new Producer(pos));
            Thread consumerThread = new Thread(new Consumer(pis));

            producerThread.start();
            consumerThread.start();

            producerThread.join();
            consumerThread.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

@Slf4j
class Producer implements Runnable {

    private PipedOutputStream pos;
    private Random random = new Random();

    public Producer(PipedOutputStream pos) {
        this.pos = pos;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                int number = random.nextInt(100);
                String message = "Number: " + number + "\n";
                pos.write(message.getBytes());
                log.info("Producer: 生成数据 - {}", number);
                // 模拟生产时间
                Thread.sleep(1000);
            }
            pos.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

@Slf4j
class Consumer implements Runnable {

    private PipedInputStream pis;

    public Consumer(PipedInputStream pis) {
        this.pis = pis;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(pis));
            String line;
            while ((line = reader.readLine()) != null) {
                log.info("Consumer: 消费数据 - {}", line.trim());
            }
            pis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
