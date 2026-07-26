package org.design_pattern.section_03_template.case_02;

/**
 * 线程模板测试
 *
 * @author lingwh
 * @date 2023/12/7 18:27
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
