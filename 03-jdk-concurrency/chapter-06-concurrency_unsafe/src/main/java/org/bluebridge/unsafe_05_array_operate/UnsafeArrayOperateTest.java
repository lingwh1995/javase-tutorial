package org.bluebridge.unsafe_05_array_operate;

import org.bluebridge.util.UnsafeAccessor;
import sun.misc.Unsafe;

/**
 * @author lingwh
 * @desc Unsafe应用四 操作数组
 * @date 2026/7/9 00:00
 */
public class UnsafeArrayOperateTest {
    public static void main(String[] args) {
        String[] array = new String[] {"str1str1str", "str2", "str3"};
        Unsafe unsafe = UnsafeAccessor.getUnsafe();
        int baseOffset = unsafe.arrayBaseOffset(String[].class);
        System.out.println(baseOffset);
        int scale = unsafe.arrayIndexScale(String[].class);
        System.out.println(scale);

        for (int i = 0; i < array.length; i++) {
            int offset = baseOffset + scale * i;
            System.out.println(offset + " : " + unsafe.getObject(array, offset));
        }
    }
}
