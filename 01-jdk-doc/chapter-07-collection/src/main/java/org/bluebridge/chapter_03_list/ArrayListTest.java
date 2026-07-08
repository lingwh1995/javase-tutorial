package org.bluebridge.chapter_03_list;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @author ronin
 */
public class ArrayListTest {

    private List<Integer> nums;

    @Before
    public void before() {
        nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(5);
    }

    /**
     * 测试list的 set()
     */
    @Test
    public void testListSet() {
        //使用set()
        nums.set(0,100);
        System.out.println("nums = " + nums);
    }

    /**
     * 测试list的 indexOf()
     */
    @Test
    public void testListIndexOf() {
        int index = nums.indexOf(2);
        System.out.println("index = " + index);
    }

    /**
     * 测试list的 lastIndexOf()
     */
    @Test
    public void testListLastIndexOf() {
        int index = nums.lastIndexOf(3);
        System.out.println("index = " + index);
    }

    /**
     * 测试list的 listIterator()
     */
    @Test
    public void testListListIterator() {
        //从索引为0的元素开始遍历
        //ListIterator<Integer> iterator = nums.listIterator();
        //从索引为1的元素开始遍历
        ListIterator<Integer> iterator = nums.listIterator(1);
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            System.out.println("i = " + i);
        }
    }
}


