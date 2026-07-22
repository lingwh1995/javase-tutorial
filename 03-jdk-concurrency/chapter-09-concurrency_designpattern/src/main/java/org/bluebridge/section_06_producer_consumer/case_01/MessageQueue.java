package org.bluebridge.section_06_producer_consumer.case_01;

import java.util.LinkedList;

/**
 * 消息队列类，java线程之间通信
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
class MessageQueue {

    // 消息的队列集合
    private LinkedList<Message> list = new LinkedList<>();
    // 队列容量
    private int capcity;

    public MessageQueue(int capcity) {
        this.capcity = capcity;
    }

    // 获取消息
    public Message take() {
        // 检查队列是否为空
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    System.out.println("队列为空, 消费者线程等待......");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 从队列头部获取消息并返回
            Message message = list.removeFirst();
            System.out.printf("已消费消息 %s\n", message);
            list.notifyAll();
            return message;
        }
    }

    // 存入消息
    public void put(Message message) {
        synchronized (list) {
            // 检查对象是否已满
            while (list.size() == capcity) {
                try {
                    System.out.println("队列已满, 生产者线程等待......");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 将消息加入队列尾部
            list.addLast(message);
            System.out.printf("已生产消息 %s\n", message);
            list.notifyAll();
        }
    }
}
