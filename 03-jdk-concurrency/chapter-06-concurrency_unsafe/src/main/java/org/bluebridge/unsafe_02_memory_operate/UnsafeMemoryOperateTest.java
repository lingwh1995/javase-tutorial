package org.bluebridge.unsafe_02_memory_operate;

import org.bluebridge.util.UnsafeAccessor;
import org.junit.Test;
import sun.misc.Unsafe;

/**
 * Unsafe应用一    操作内存
 *
 * 1. public native long allocateMemory(long bytes);   // 分配新的本地空间
 * 2. public native long reallocateMemory(long address, long bytes);   //重新调整内存空间的大小
 * 3. public native void setMemory(Object o, long offset, long bytes, byte value);     //将内存设置为指定值
 * 4. public native void copyMemory(Object srcBase, long srcOffset,Object destBase, long destOffset,long bytes);   //内存拷贝
 * 5. public native void freeMemory(long address);     //清除内存
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class UnsafeMemoryOperateTest {

    /**
     * 测试使用Unsafe操作内存
     */
    @Test
    public void testUnsafeMemoryOperate1() {
        Unsafe unsafe = UnsafeAccessor.getUnsafe();
        // 分配4字节内存地址
        long address = unsafe.allocateMemory(4);
        System.out.println("分配内存的地址: " + address);

        // 向内存地址写入整数100
        unsafe.putInt(address, 100);
        System.out.println("写入内存成功");

        // 从内存中读取整数
        int value = unsafe.getInt(address);
        System.out.println("读取的值: " + value);

        // 释放内存
        unsafe.freeMemory(address);
        System.out.println("释放内存成功");
    }

    /**
     * 测试使用Unsafe操作内存
     */
    @Test
    public void testUnsafeMemoryOperate2() {
        int size = 4;
        Unsafe unsafe = UnsafeAccessor.getUnsafe();
        long addr = unsafe.allocateMemory(size);
        long addr3 = unsafe.reallocateMemory(addr, size * 2);
        System.out.println("addr: " + addr);
        System.out.println("addr3: " + addr3);
        try {
            unsafe.setMemory(null, addr, size, (byte) 1);
            for (int i = 0; i < 2; i++) {
                unsafe.copyMemory(null, addr, null, addr3 + size * i, 4);
            }
            System.out.println(unsafe.getInt(addr));
            System.out.println(unsafe.getLong(addr3));
        } finally {
            unsafe.freeMemory(addr);
            unsafe.freeMemory(addr3);
        }
    }
}
