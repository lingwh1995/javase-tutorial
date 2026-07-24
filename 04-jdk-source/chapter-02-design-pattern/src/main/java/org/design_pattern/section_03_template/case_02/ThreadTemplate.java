package org.design_pattern.section_03_template.case_02;

/**
 * 线程模板
 *
 * @author lingwh
 * @date 2026/7/13 10:30
 */
public abstract class ThreadTemplate {

    public final void start() {
        System.out.println("-------------------");
        run();
        System.out.println("-------------------");
    }

    protected abstract void run();
}
