package stack.stack_b;

import java.util.NoSuchElementException;

/**
 * 链表模拟栈
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class LinkedListStack {

    private SingleLinkedList singleLinkedList = new SingleLinkedList();
    private int elementIndex = 0;

    /**
     * 压栈
     *
     * @param element
     */
    public void push(String element) {
        Node node = new Node(++elementIndex, element);
        singleLinkedList.add(node);
    }

    /**
     * 弹栈
     *
     * @return
     */
    public String pop() {
        Node node = singleLinkedList.removeLast();
        if (node == null) {
            throw new NoSuchElementException();
        }
        return node.data;
    }

    /**
     * 打印栈
     */
    public void show() {
        singleLinkedList.reserve();
        System.out.println("----------------");
    }

    /**
     * 栈中元素个数
     *
     * @return
     */
    public int size() {
        return singleLinkedList.size();
    }
}

class SingleLinkedList {
    private Node head = new Node(0, "");

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        Node tempNode = head;
        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }
        tempNode.next = node;
    }

    /**
     * 删除最后一个节点
     *
     * @return
     */
    public Node removeLast() {
        if (head.next == null) {
            return null;
        }
        Node tempNode = head;
        while (tempNode.next.next != null) {
            tempNode = tempNode.next;
        }
        Node node = tempNode.next;
        tempNode.next = tempNode.next.next;
        return node;
    }

    /**
     * 统计节点个数
     */
    public int size() {
        Node tempNode = head;
        int size = 0;
        while (tempNode.next != null) {
            tempNode = tempNode.next;
            size++;
        }
        return size;
    }

    /**
     * 反转打印链表
     */
    public void reserve() {
        reserve(head);
    }

    /**
     * 递归打印链表
     *
     * @param head
     */
    public void reserve(Node head) {
        if (head.next == null) {
            return;
        }
        reserve(head.next);
        System.out.println(head.next.data);
    }

    /**
     * 打印节点
     */
    public void show() {
        if (null == head.next) {
            System.out.println("链表为空...");
            return;
        }
        // 遍历实现方式一
        // Node tempNode = head.next;
        // while(tempNode != null){
        // System.out.println(tempNode);
        // tempNode = tempNode.next;
        // }
        // 遍历实现方式二
        Node tempNode = null;
        for (tempNode = head.next; tempNode != null; tempNode = tempNode.next) {
            System.out.println(tempNode);
        }
    }
}

class Node {

    protected int id;
    protected String data;
    protected Node next;

    public Node(int id, String data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" + "id=" + id + ", data='" + data + '\'' + '}';
    }
}
