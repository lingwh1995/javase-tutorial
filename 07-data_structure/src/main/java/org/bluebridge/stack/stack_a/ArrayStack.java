package org.bluebridge.stack.stack_a;

/**
 * 数组模拟栈
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class ArrayStack {

    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 判断栈是否已满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 压栈
     *
     * @param element
     */
    public void push(int element) {
        if (isFull()) {
            System.out.println("栈已满......");
            return;
        }
        top++;
        stack[top] = element;
    }

    /**
     * 弹栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空......");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 获取栈中真实元素的个数
     *
     * @return
     */
    public int size() {
        return top + 1;
    }

    /**
     * 遍历栈
     */
    public void show() {
        for (int i = top; i >= 0; i--) {
            System.out.print(stack[i] + "\t");
        }
        System.out.println();
    }
}
