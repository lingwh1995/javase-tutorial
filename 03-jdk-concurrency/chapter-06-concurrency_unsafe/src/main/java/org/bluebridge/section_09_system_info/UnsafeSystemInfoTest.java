package org.bluebridge.section_09_system_info;

import org.bluebridge.util.UnsafeAccessor;
import org.junit.Test;
import sun.misc.Unsafe;

/**
 * Unsafe 应用八 系统信息
 *
 * @author lingwh
 * @date 2026/7/13 13:30
 */
public class UnsafeSystemInfoTest {

    @Test
    public void testUnsafeSystemInfoTest() throws Exception {
        Unsafe unsafe = UnsafeAccessor.getUnsafe();
        System.out.println(unsafe.addressSize());
        System.out.println(unsafe.pageSize());
    }
}
