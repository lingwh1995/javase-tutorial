package org.bluebridge.queue.queue_b;

/**
 * 环形队列测试
 *
 * @author lingwh
 * @date 2026/7/22 20:14
 */
public class CircleQueueTest {

    public static void main(String[] args) {
        // 队列长度为 4，最多添加 3 个元素，因为有一个空位
        CircleQueue circleQueue = new CircleQueue(4);
        // 给队列中添加 3 个元素，第 4 个元素添加不进去
        circleQueue.addElement(10);
        circleQueue.show();
        circleQueue.addElement(20);
        circleQueue.show();
        circleQueue.addElement(30);
        circleQueue.show();
        circleQueue.addElement(40);
        circleQueue.show();
        // 取出队列中的第 1 个元素，再次给队列中添加 1 个元素
        System.out.println("从队列中取出的元素为：" + circleQueue.getElement());
        circleQueue.addElement(40);
        circleQueue.show();
        // 取出队列中的第 2 个元素，再次给队列中添加 2 个元素
        System.out.println("从队列中取出的元素为：" + circleQueue.getElement());
        circleQueue.show();
        System.out.println("从队列中取出的元素为：" + circleQueue.getElement());
        circleQueue.addElement(50);
        circleQueue.show();
        circleQueue.addElement(60);
        circleQueue.show();
        System.out.println("从队列中取出的元素为：" + circleQueue.getElement());
        circleQueue.show();
    }
}
