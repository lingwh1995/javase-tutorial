package org.design_pattern.template.template_b;

/**
 * @author lingwh
 * @desc 线程模板
 * @date 2026/7/9 00:00
 */
public abstract class ThreadTemplate {

    public final void start() {
        System.out.println("-------------------");
        run();
        System.out.println("-------------------");
    }

    protected abstract void run();
}
