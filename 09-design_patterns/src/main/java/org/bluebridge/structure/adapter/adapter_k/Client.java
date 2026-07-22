package org.bluebridge.structure.adapter.adapter_k;

import java.util.Enumeration;
import java.util.Stack;

/**
 * 适配器模式客户端
 *
 * @author lingwh
 * @date 2019/9/11 17:16
 */
public class Client {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        stack.add("1");
        stack.add("2");
        stack.add("3");
        stack.add("4");
        stack.add("5");
        Enumeration<String> elements = stack.elements();
        // 遍历Stack
        foreach(elements);
    }

    /**
     * 遍历枚举
     *
     * @param elements
     */
    public static void foreach(Enumeration elements) {
        EnumerationIterator enumerationIterator = new EnumerationIterator(elements);
        while (enumerationIterator.hasNext()) {
            System.out.println(enumerationIterator.next());
        }
    }
}
