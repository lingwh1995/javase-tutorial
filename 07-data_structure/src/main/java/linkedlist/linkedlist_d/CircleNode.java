package linkedlist.linkedlist_d;

/**
 * 模拟环形链表(注意：并非是真正的环形链表结构)
 *
 * 1. 循环链表的最后一个节点的指针域指向链表的第一个节点
 * 2. 循环链表没有最后一个节点，所以也不需要有判断是否为最后一个节点的方法
 * 3. 当循环链表只有一个节点的时候，下一个节点就是当前节点本身)
 *
 * @author lingwh
 * @date 2019/3/31 10:01
 *
 */
public class CircleNode<T> {

    public static void main(String[] args) {
        // 创建节点
        CircleNode<String> node1 = new CircleNode<String>("1");
        CircleNode<String> node2 = new CircleNode<String>("2");
        CircleNode<String> node3 = new CircleNode<String>("3");
        CircleNode<String> node4 = new CircleNode<String>("4");

        // 增加节点
        node1.after(node2);
        node2.after(node3);
        node3.after(node4);
        System.out.println("Node1的下一个节点中的值:" + node1.next().getData());
        System.out.println("Node2的下一个节点中的值:" + node2.next().getData());
        System.out.println("Node3的下一个节点中的值:" + node3.next().getData());
    }

    // 节点内容
    private T data;
    // 下一个节点
    private CircleNode<T> next = this;

    /**
     * 有参构造
     */
    public CircleNode(T data) {
        this.data = data;
    }

    // public void append(Node<T> node){
    // this.next = node;
    // }

    /**
     * 获取下一个节点
     *
     * @param
     * @return Node
     * @throws
     */
    public CircleNode<T> next() {
        return this.next;
    }

    /**
     * 获取节点中的数据
     *
     * @param @return
     * @return T
     * @throws
     */
    public T getData() {
        return this.data;
    }

    /**
     * 删除下一个节点
     *
     * @param
     * @return void
     * @throws
     */
    public void removeNext() {
        System.out.println("删除了" + this.next.getClass().getSimpleName() + this.next.data + "节点!");
        // 获取下下一个节点
        CircleNode<T> newNext = next.next;
        // 把下下一个节点设置为当前节点的下一个节点
        this.next = newNext;
    }

    /**
     * 插入一个节点作为当前节点的下一个节点: 循环链表中，在当前节点的下一个节点插入数据之前，当前节点的下一个节点始终指向第一个节点
     *
     * @return void
     * @throws
     */
    public void after(CircleNode<T> node) {
        // 1.获取到当前节点的下一个节点作为当前节点的下下一个节点
        CircleNode<T> first = this.next;
        System.out.println("当值节点中存放的值:" + this.data);
        System.out.println("当前节点的下一个节点中存放的值:" + this.next.data);
        // 2.设置插入的节点作为当前节点的下一个节点
        this.next = node;
        // 2.设置插入的节点的下一个节点为当前节点的下下一个节点
        node.next = first;
    }

    @Override
    public String toString() {
        return "LoopNode{" + "data=" + data + '}';
    }
}
