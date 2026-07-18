package org.bluebridge;

import org.junit.Test;

/**
 * 使用final变量
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class UseFinal1 {

    @Test
    public void test() {
        System.out.println(FinalTest.A);
        System.out.println(FinalTest.B);
        System.out.println(new FinalTest().a);
        System.out.println(new FinalTest().b);
        new FinalTest().test1();
    }
}
