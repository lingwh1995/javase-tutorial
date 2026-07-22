package org.bluebridge.linkedlist.linkedlist_d;

/**
 * 双向循环链表 - 当只有一个节点的时候，当前节点的下一个节点和上一个节点都是当前节点
 *
 * @author lingwh
 * @date 2019/3/31 19:02
 */
public class DoubleCircleNode {

    public static void main(String[] args) {
        DoubleCircleNode node1 = new DoubleCircleNode(1);
        DoubleCircleNode node2 = new DoubleCircleNode(2);
        DoubleCircleNode node3 = new DoubleCircleNode(3);

        // 追加节点
        System.out.println("当前节点中的上一个节点中的数据:" + node1.pre().getData());
        System.out.println("当前节点中的数据:" + node1.getData());
        System.out.println("当前节点中的下一个节点中的数据:" + node1.next().getData());
        node1.after(node2);
        System.out.println("--------------------------------------------------");
        System.out.println("当前节点中的上一个节点中的数据:" + node1.pre().getData());
        System.out.println("当前节点中的数据:" + node1.getData());
        System.out.println("当前节点中的下一个节点中的数据:" + node1.next().getData());
        node2.after(node3);
        System.out.println("--------------------------------------------------");
        System.out.println("当前节点中的上一个节点中的数据:" + node2.pre().getData());
        System.out.println("当前节点中的数据:" + node2.getData());
        System.out.println("当前节点中的下一个节点中的数据:" + node2.next().getData());
        System.out.println("--------------------------------------------------");
        System.out.println("当前节点中的上一个节点中的数据:" + node3.pre().getData());
        System.out.println("当前节点中的数据:" + node3.getData());
        System.out.println("当前节点中的下一个节点中的数据:" + node3.next().getData());
    }

    // 上一个节点
    DoubleCircleNode pre = this;

    // 下一个节点
    DoubleCircleNode next = this;

    // 节点数据
    int data;

    /**
     * 创建一个新的实例 DoubleLoopNode.
     *
     * @param data
     */
    public DoubleCircleNode(int data) {
        this.data = data;
    }

    /**
     * 增加节点
     *
     * @param node
     * @return void
     * @throws
     */
    public void after(DoubleCircleNode node) {
        // 原来节点的下一个节点
        DoubleCircleNode nextNext = this.next;
        // 把新节点作为当前节点的下一个节点
        this.next = node;
        // 把当前节点作为新节点的前一个节点
        node.pre = this;
        // 将新节点的下一个节点设置为原来节点的下一个节点
        node.next = nextNext;
        // 让原来节点的下一个节点为上一个节点的新节点
        nextNext.pre = node;
    }

    /**
     * 获取当前节点的下一个节点
     *
     * @param
     * @return DoubleLoopNode
     * @throws
     */
    public DoubleCircleNode next() {
        return this.next;
    }

    /**
     * 获取当前节点的上一个节点
     *
     * @param
     * @return DoubleLoopNode
     * @throws
     */
    public DoubleCircleNode pre() {
        return this.pre;
    }

    /**
     * 获取当前节点中的数据
     *
     * @param
     * @return int
     * @throws
     */
    public int getData() {
        return this.data;
    }
}
