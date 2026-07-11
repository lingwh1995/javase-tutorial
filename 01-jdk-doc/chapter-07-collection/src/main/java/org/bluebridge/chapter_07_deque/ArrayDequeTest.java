package org.bluebridge.chapter_07_deque;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;


/**
 * @author lingwh
 * @desc ArrayDeque使用测试
 * @date 2026/7/9 00:00
 */
/**
 * @author lingwh
 * @desc ArrayDeque使用测试
 *       ArrayDeque特点介绍
 *         1.动态数组实现
 *           ArrayDeque 使用数组作为底层数据结构，能够根据需要自动扩容。
 *           默认初始容量为 16，当元素数量超过容量时，数组会动态扩展为原来的两倍。
 *         2.高效性能
 *           在队列的两端（头部和尾部）添加和移除元素的时间复杂度为 O(1)。
 *           相比于 LinkedList，ArrayDeque 在大多数情况下性能更好，因为它基于数组实现，内存访问更高效。
 *         3.非线程安全
 *           ArrayDeque 不是线程安全的。如果需要在多线程环境中使用，需要手动同步或使用 Collections.synchronizedDeque() 包装。
 *         4.不允许 null 元素
 *           ArrayDeque 不允许插入 null 元素，否则会抛出 NullPointerException。
 *         5.用途广泛
 *           可以用作栈（Stack）、队列（Queue）或双端队列（Deque）。
 *
 *       常用方法
 *         添加元素
 *           void addFirst(E e): 在队列头部插入元素，如果队列已满则抛出异常，由于会自动扩容，一般不会满。
 *           void addLast(E e): 在队列尾部插入元素，如果队列已满则抛出异常，由于会自动扩容，一般不会满。
 *           boolean offerFirst(E e): 在队列头部插入元素，成功返回 true，失败返回 false。
 *           boolean offerLast(E e): 在队列尾部插入元素，成功返回 true，失败返回 false。
 *
 *         移除元素
 *           E removeFirst(): 移除并返回队列头部的元素，如果队列为空则抛出异常。
 *           E removeLast(): 移除并返回队列尾部的元素，如果队列为空则抛出异常。
 *           E pollFirst(): 移除并返回队列头部的元素，如果队列为空则返回 null。
 *           E pollLast(): 移除并返回队列尾部的元素，如果队列为空则返回 null。
 *
 *         查看元素
 *           E getFirst(): 返回队列头部的元素，如果队列为空则抛出异常。
 *           E getLast(): 返回队列尾部的元素，如果队列为空则抛出异常。
 *           E peekFirst(): 返回队列头部的元素，如果队列为空则返回 null。
 *           E peekLast(): 返回队列尾部的元素，如果队列为空则返回 null。
 *
 *         其他方法
 *           size(): 返回队列中的元素数量。
 *           isEmpty(): 判断队列是否为空。
 *           clear(): 清空队列中的所有元素。
 *
 *        使用场景
 *           作为栈（Stack）：
 *               使用 addFirst() 和 removeFirst() 或 push() 和 pop() 方法实现栈的先进后出（LIFO）行为。
 *               替代 Stack 类（Stack 是 Java 早期的遗留类，性能较差）。
 *           作为队列（Queue）：
 *               使用 addLast() 和 removeFirst() 或 offer() 和 poll() 方法实现队列的先进先出（FIFO）行为。
 *           作为双端队列（Deque）：
 *               支持在队列的两端添加和移除元素，适合需要双向操作的场景。
 * @date 2026/7/9 00:00
 */
public class ArrayDequeTest {

    /**
     * ArrayDeque基本使用
     */
    @Test
    public void testArrayDequeHelloWorld() {
        Deque<Integer> deque = new ArrayDeque<>();
        // 在队首添加元素1
        deque.addFirst(1);
        // 在队尾添加元素2
        deque.addLast(2);
        // 访问队首元素，输出1
        System.out.println(deque.getFirst());
        // 访问队尾元素，输出2
        System.out.println(deque.getLast());
        // 删除队首元素
        deque.removeFirst();
        // 再次访问队首元素，输出2
        System.out.println(deque.getFirst());
    }

    /**
     * ArrayDeque作为栈使用
     */
    @Test
    public void testArrayDequeAsStack() {
        Deque<String> stack = new ArrayDeque<>();

        // 入栈
        stack.push("A");
        stack.push("B");
        stack.push("C");

        // 出栈
        while (!stack.isEmpty()) {
            System.out.println(stack.pop()); // 输出: C, B, A
        }
    }

    /**
     * ArrayDeque作为队列使用
     */
    @Test
    public void testArrayDequeAsQueue() {
        Deque<String> queue = new ArrayDeque<>();

        // 入队
        queue.offer("A");
        queue.offer("B");
        queue.offer("C");

        // 出队   输出: A, B, C
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    /**
     * ArrayDeque作为双端队列使用
     */
    @Test
    public void testArrayDequeAsDeque() {
        Deque<String> deque = new ArrayDeque<>();

        // 在头部和尾部添加元素
        deque.addFirst("A");
        deque.addLast("B");
        deque.addFirst("C");

        // 查看并移除元素
        System.out.println(deque.pollFirst()); // 输出: C
        System.out.println(deque.pollLast()); // 输出: B
    }
}
