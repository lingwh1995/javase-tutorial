package org.bluebridge.unsafe_01_hello_world;

import org.bluebridge.util.UnsafeAccessor;
import org.junit.Test;
import sun.misc.Unsafe;

/**
 * 测试获取Unsafe实例
 *
 * @author lingwh
 * @date 2026/7/13 19:02
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
