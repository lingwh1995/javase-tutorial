package org.bluebridge.expand.principles.interface_segregation;

import org.junit.Test;

/**
 * 调用 Interface2 的客户端
 *
 * @author lingwh
 * @date 2026/7/22 14:21
 */
public class Interface2Client {

    /**
     * 测试 A2 通过接口 Interface2A，Interface2B 依赖 B2
     */
    @Test
    public void testA2() {
        A2 a2 = new A2();
        a2.depend1(new B2());
        a2.depend2(new B2());
        a2.depend3(new B2());
    }

    /**
     * 测试 C2 通过接口 Interface2B，Interface2C 依赖 D2
     */
    @Test
    public void testC2() {
        C2 c2 = new C2();
        c2.depend1(new D2());
        c2.depend4(new D2());
        c2.depend5(new D2());
    }
}
