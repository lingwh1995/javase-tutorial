package org.bluebridge.api;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 字节序测试
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class ByteOrderTest2 {

    public static void main(String[] args) throws InterruptedException {
        Unsafe unsafe = reflectGetUnsafe();
        long a = unsafe.allocateMemory(4L);
        try {
            unsafe.putLong(a, 0x12345678L);
            // 存放此 long 类型数据，实际存放占 8 个字节，01,02,03，04,05,06,07,08
            byte b = unsafe.getByte(a);
            // 通过 getByte 方法获取刚才存放的 long，取第一个字节
            // 如果是大端，long 类型顺序存放—》01,02,03,04,05,06,07,08 ，取第一位便是 0x01
            // 如果是小端，long 类型顺序存放—》08,07,06,05,04,03,02,01 ，取第一位便是 0x08
            switch (b) {
                case 0x12:
                    System.out.println("大端");
                    break;
                case 0x78:
                    System.out.println("小端");
                    break;
                default:
                    assert false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
