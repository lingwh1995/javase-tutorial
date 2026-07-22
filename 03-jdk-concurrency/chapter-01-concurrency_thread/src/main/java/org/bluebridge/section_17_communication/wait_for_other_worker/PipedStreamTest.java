package org.bluebridge.section_17_communication.wait_for_other_worker;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 演示PipedInputStream/PipedOutputStream机制的使用场景：主线程等待多个工作线程完成
 *
 * @author lingwh
 * @date 2025/10/28 9:34
 */
@Slf4j
public class PipedStreamTest {

    private static final int TOTAL_WORKERS = 3;
    // 创建管道输出流和输入流
    private static final PipedOutputStream[] pipedOutputStreams = new PipedOutputStream[TOTAL_WORKERS];
    private static final PipedInputStream pipedInputStream = new PipedInputStream();

    static {
        try {
            // 连接管道
            for (int i = 0; i < TOTAL_WORKERS; i++) {
                pipedOutputStreams[i] = new PipedOutputStream();
                pipedInputStream.connect(pipedOutputStreams[i]);
            }
        } catch (IOException e) {
            log.error("管道连接失败", e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 启动3个工作线程
        new Thread(new Worker("工作线程1 => 启动服务A", pipedOutputStreams[0], 0)).start();
        new Thread(new Worker("工作线程2 => 启动服务B", pipedOutputStreams[1], 1)).start();
        new Thread(new Worker("工作线程3 => 启动服务C", pipedOutputStreams[2], 2)).start();

        log.info("主线程等待所有工作线程完成......");

        // 从管道中读取完成信号
        int completedCount = 0;
        try {
            while (completedCount < TOTAL_WORKERS) {
                pipedInputStream.read(); // 阻塞读取
                completedCount++;
            }
        } catch (IOException e) {
            log.error("读取管道数据失败", e);
        }

        log.info("所有工作已完成，主线程继续执行......");

        // 关闭流
        try {
            pipedInputStream.close();
            for (PipedOutputStream pos : pipedOutputStreams) {
                if (pos != null) {
                    pos.close();
                }
            }
        } catch (IOException e) {
            log.error("关闭流失败", e);
        }
    }

    static class Worker implements Runnable {
        private final String name;
        private final PipedOutputStream outputStream;
        private final int index;

        public Worker(String name, PipedOutputStream outputStream, int index) {
            this.name = name;
            this.outputStream = outputStream;
            this.index = index;
        }

        @Override
        public void run() {
            try {
                log.info("{} 开始工作......", name);
                // 模拟工作耗时
                Thread.sleep((long) (Math.random() * 3000));
                log.info("{} 工作完成......", name);

                // 向管道写入完成信号
                outputStream.write(1); // 写入一个字节作为完成信号
                outputStream.flush();
            } catch (InterruptedException | IOException e) {
                Thread.currentThread().interrupt();
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("{} 关闭输出流失败", name, e);
                }
            }
        }
    }
}
