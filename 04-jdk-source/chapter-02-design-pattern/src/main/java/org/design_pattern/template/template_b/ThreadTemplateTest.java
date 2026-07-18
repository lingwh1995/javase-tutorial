package org.design_pattern.template.template_b;

/**
 * 线程模板测试
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class ThreadTemplateTest {

    public static void main(String[] args) {
        ThreadTemplate tm = new ThreadTemplate() {
            @Override
            public void run() {
                System.out.println("线程启动了......");
            }
        };
        tm.start();
    }
}
