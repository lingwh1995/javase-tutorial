package org.bluebridge;

import org.junit.Test;

/**
 * @author lingwh
 * @desc 使用final变量
 * @date 2026/7/9 00:00
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
