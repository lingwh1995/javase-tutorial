package org.bluebridge.reflect.chapter_06_reflect_demo.lesson_02_my_junit;

/**
 * 自定义Junit测试用例
 *
 * @author lingwh
 * @date 2026/6/22 18:04
 */
public class MyJunit {

    @Before
    public void before() {
        System.out.println("before方法执行了...");
    }

    @Test
    public void test() {
        System.out.println("test方法执行了...");
    }

    @After
    public void after() {
        System.out.println("after方法执行了...");
    }
}
