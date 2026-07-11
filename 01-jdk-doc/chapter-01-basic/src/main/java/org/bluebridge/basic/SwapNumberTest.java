package org.bluebridge.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 交换数字测试
 *
 * java中的值传递: 就是把实参的值复制一份作为参数传递给方法的形参
 * 注意: 在C语言中有值传递和引用传递，引用传递就是指针传递
 *
 * @author lingwh
 * @date 2019/7/10 13:39
 */
@Slf4j
public class SwapNumberTest {

    @Test
    public void testSwapNumber() {
        // 测试交换失败
        int a = 10;
        int b = 20;
        swap_1(a, b);
        log.info("a = {}, b = {}}", a, b);

        // 测试使用数组成功交换两个数字
        int c = 10;
        int d = 20;
        int[] arr = {c, d};
        swap_2(arr);
        log.info("c = {}, d = {}}", arr[0], arr[1]);

        // 测试使用泛型数组成功交换两个数字
        int e = 10;
        int f = 20;
        int[] arr_t = {e, f};
        swap_2(arr_t);
        log.info("e = {}, f = {}}", arr_t[0], arr_t[1]);
    }

    /**
     * 无法交换成功
     *
     * @param a
     * @param b
     */
    public static void swap_1(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }

    /**
     * 使用数组实现交换两个数
     * 注意: 此处可以直接返回数组,也可以不返回数组
     *
     * @param arr
     */
    public static void swap_2(int[] arr) {
        arr[0] = arr[0] ^ arr[1];
        arr[1] = arr[0] ^ arr[1];
        arr[0] = arr[0] ^ arr[1];
    }

    /**
     * 使用泛型数组实现交换两个数
     *
     * @param arr
     */
    public static <T> T[] swap_2(T[] arr) {
        T temp = arr[0];
        arr[0] = arr[1];
        arr[1] = temp;
        return arr;
    }
}
