package queue.queue_b;

/**
 * 数组模拟环形队列
 *    队列尾部的后一个元素就是队列头部的第一个元素，因为是环形队列
 * 环形队列好处
 *    能把数组中空出来的位置再次利用起来
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class CircleQueue {

    /**
     * 队列的最大容量
     */
    private int maxCapacity;

    /**
     * 队列头指针:指向队列中第一个元素
     */
    private int front;

    /**
     * 列尾指针:指向队列中的最后一个元素的后一个元素
     */
    private int rear;

    /**
     * 用于存放队列中的元素
     */
    private int[] elements;

    /**
     * 队列中可以存放的最大的元素的个数
     *
     * @param maxCapacity
     */
    public CircleQueue(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.front = 0;
        this.rear = 0;
        this.elements = new int[maxCapacity];
    }

    /**
     * 判读环形队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 判读环形队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxCapacity == front;
    }

    /**
     * 给队列中添加元素
     *
     * @param element
     */
    public void addElement(int element) {
        if (isFull()) {
            System.out.println("队列已满,无法添加元素" + element + "到当前队列......");
            return;
        }
        elements[rear] = element;
        this.rear = (rear + 1) % maxCapacity;
    }

    /**
     * 获取对列元素（返回队列头部的元素）
     *
     * @return
     */
    public int getElement() {
        if (isEmpty()) {
            throw new IllegalStateException("当前队列中没有元素,无法继续执行出队列操作~");
        }
        int element = elements[front];
        this.front = (front + 1) % maxCapacity;
        return element;
    }

    /**
     * 打印数组
     */
    public void show() {
        System.out.print("当前数组为:[  ");
        for (int i = front; i < front + getQueueRealElementSize(); i++) {
            System.out.print(elements[i % maxCapacity] + "  ");
        }
        System.out.print("]，头指针位置：" + front + "，尾指针位置：" + rear + "\n");
    }

    /**
     * 获取队列中真实元素的个数
     *
     * @return
     */
    private int getQueueRealElementSize() {
        return (rear + maxCapacity - front) % maxCapacity;
    }
}
