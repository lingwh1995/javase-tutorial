package stack.stack_a;

import org.junit.Test;

/**
 * @author lingwh
 * @desc 数组栈测试
 * @date 2026/7/9 00:00
 */
public class ArrayStackTest {

    @Test
    public void fun() {
        ArrayStack arrayStack = new ArrayStack(10);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        System.out.println("栈的实际元素个数：" + arrayStack.size());
        arrayStack.show();
        arrayStack.pop();
        arrayStack.pop();
        System.out.println("栈的实际元素个数：" + arrayStack.size());
        arrayStack.show();
    }
}
