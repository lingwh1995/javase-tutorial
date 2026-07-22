package org.bluebridge.section_03_list;

import java.util.LinkedList;

/**
 * LinkedList常用方法测试
 *
 * @author lingwh
 * @date 2026/7/8 18:39
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
