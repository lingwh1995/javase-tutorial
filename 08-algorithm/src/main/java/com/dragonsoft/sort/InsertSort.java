package com.dragonsoft.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author lingwh
 * @date 2019/3/2 19:02
 */
public class InsertSort {

    @Test
    public void fun() {
        int[] num = { 7, 5, 4, 1, 8, 6, 3, 2, 9 };
        System.out.println("没有排序之前的数组是:" + Arrays.toString(num));
        for (int i = 1; i < num.length; i++) {
            int j = i;
            while (j > 0 && num[j] < num[j - 1]) {
                num[j] = num[j] ^ num[j - 1];
                num[j - 1] = num[j] ^ num[j - 1];
                num[j] = num[j] ^ num[j - 1];
                j--;
            }
            System.out.println("第" + i + "次排序后的数组是:" + Arrays.toString(num));
        }
    }
}
