package org.bluebridge.api;

import org.junit.Test;

import java.util.Arrays;

/**
 * System 类 API 测试
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class SystemTest {

    /*
     * System 的 arraycopy() 方法，此方法常用来执行比较复杂的数组拷贝
     *
     * @param src the source array.
     * @param srcPos starting position in the source array.
     * @param dest the destination array.
     * @param destPos starting position in the destination data.
     * @param length the number of array elements to be copied.
     *
     * System.arraycopy(src,srcPos,dest,destPos,length)
     */
    @Test
    public void arraycopyTest() {
        String[] strs = { "a", "b", "c", "d", "e" };
        String[] newShortThanOldArray = { "null", "null", "null", "null", "null" };
        System.arraycopy(strs, 0, newShortThanOldArray, 1, 2);
        System.out.println("newShortThanOldArray:" + Arrays.toString(newShortThanOldArray));

        String[] newLongThanOldArray = { "null", "null", "null", "null", "null", "null", "null", "null", "null", "null" };
        System.arraycopy(strs, 1, newLongThanOldArray, 2, 3);
        System.out.println("newLongThanOldArray:" + Arrays.toString(newLongThanOldArray));
    }
}
