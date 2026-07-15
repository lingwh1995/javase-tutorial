package org.bluebridge.test;

import org.bluebridge.enumeration.ModifyEnum;
import org.junit.Test;

/**
 * ModifyEnum测试
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class ModifyEnumTest {

    /**
     * 测试获取枚举中存储的值
     */
    @Test
    public void test() {
        ModifyEnum[] modifies = ModifyEnum.values();
        for (ModifyEnum modify : modifies) {
            System.out.println(modify.getCode() + ":" + modify.getDesc());
        }
    }
}
