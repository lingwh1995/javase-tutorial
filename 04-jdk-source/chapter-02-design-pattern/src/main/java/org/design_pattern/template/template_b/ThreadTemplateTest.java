package org.design_pattern.template.template_b;

/**
 * @author lingwh
 * @desc 线程模板测试
 * @date 2026/7/9 00:00
 */
public class ThreadTemplateTest {
    public static void main(String[] args) {
        ThreadTemplate tm = new ThreadTemplate(){
            @Override
            public void run() {
                System.out.println("线程启动了......");
            }
        };
        tm.start();
    }
}
