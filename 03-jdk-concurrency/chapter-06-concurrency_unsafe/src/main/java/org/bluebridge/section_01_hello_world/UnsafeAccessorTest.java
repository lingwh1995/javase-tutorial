package org.bluebridge.section_01_hello_world;

import org.bluebridge.util.UnsafeAccessor;
import org.junit.Test;
import sun.misc.Unsafe;

/**
 * 测试获取 Unsafe 实例
 *
 * @author lingwh
 * @date 2026/7/13 10:30
 */
public class UnsafeAccessorTest {

    /**
     * 测试获取 Unsafe 实例
     */
    @Test
    public void testUnsafeAccessor() {
        Unsafe unsafe = UnsafeAccessor.getUnsafe();
        System.out.println("unsafe = " + unsafe);
    }
}
