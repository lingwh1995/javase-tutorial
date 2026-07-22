package org.bluebridge.queue.queue_b;

/**
 * 环形队列测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class CircleQueueTest {

    public static void main(String[] args) {
        // 队列长度为4，最多添加3个元素，因为有一个空位
        CircleQueue circleQueue = new CircleQueue(4);
        // 给队列中添加3个元素，第4个元素添加不进去
        circleQueue.addElement(10);
        circleQueue.show();
        circleQueue.addElement(20);
        circleQueue.show();
        circleQueue.addElement(30);
        circleQueue.show();
        circleQueue.addElement(40);
        circleQueue.show();
        // 取出队列中的第1个元素，再次给队列中添加1个元素
        System.out.println("从队列中取出的元素为：" + circleQueue.getElement());
        circleQueue.addElement(40);
        circleQueue.show();
        // 取出队列中的第2个元素，再次给队列中添加2个元素
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
