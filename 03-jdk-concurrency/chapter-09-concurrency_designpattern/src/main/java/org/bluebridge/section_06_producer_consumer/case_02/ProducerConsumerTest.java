package org.bluebridge.section_06_producer_consumer.case_02;

/**
 * 生产者消费者模式
 *
 * @author lingwh
 * @date 2025/3/21 15:08
 */
public class ProducerConsumerTest {

    private boolean flag = false;

    /**
     * 消费者
     */
    public void producer() {
        synchronized (this) {
            while (flag) {
                try {
                    this.wait(); // 如果 flag 为 true，则等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("生产者开始生产......");
            // 这里模拟生产的过程
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("生产者生产完毕，通知消费者消费......");
            flag = true;
            this.notify(); // 生产完毕，通知消费者
        }
    }

    /**
     * 生产者
     */
    public void consumer() {
        synchronized (this) {
            while (!flag) {
                try {
                    this.wait(); // 如果 flag 为 false，则等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("消费者开始消费......");
            // 这里模拟消费的过程
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("消费者消费完毕，通知生产者生产......");
            flag = false;
            this.notify(); // 消费完毕，通知生产者
        }
    }

    public static void main(String[] args) {
        ProducerConsumerTest producerConsumer = new ProducerConsumerTest();

        // 创建生产者和消费者线程
        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                producerConsumer.producer();
            }
        });

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                producerConsumer.consumer();
            }
        });

        // 启动线程
        producerThread.start();
        consumerThread.start();
    }
}
