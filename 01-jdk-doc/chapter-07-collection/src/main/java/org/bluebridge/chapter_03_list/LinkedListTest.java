package org.bluebridge.chapter_03_list;

import java.util.LinkedList;

/**
 * @author lingwh
 * @desc LinkedList常用方法测试
 * @date 2026/7/9 00:00
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        System.out.println(list);

        list.addFirst("first");
        list.addLast("last");
        System.out.println(list);

        System.out.println(list.getFirst());
        System.out.println(list.getLast());

        System.out.println("-----------------");
        System.out.println("removeFirst:" + list.removeFirst());
        System.out.println(list);
        System.out.println("removeLast:" + list.removeLast());
        System.out.println(list);
    }
}
