package org.bluebridge.unsafe_01_hello_world;

import org.bluebridge.util.UnsafeAccessor;
import org.junit.Test;
import sun.misc.Unsafe;

/**
 * @author lingwh
 * @desc 测试获取Unsafe实例
 * @date 2026/7/9 00:00
 */
public class UnsafeAccessorTest {

    /**
     * 测试获取Unsafe实例
     */
    @Test
    public void testUnsafeAccessor() {
        Unsafe unsafe = UnsafeAccessor.getUnsafe();
        System.out.println("unsafe = " + unsafe);
    }
}
