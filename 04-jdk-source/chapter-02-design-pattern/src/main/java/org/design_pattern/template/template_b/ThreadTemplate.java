package org.design_pattern.template.template_b;

/**
 * 线程模板
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public abstract class ThreadTemplate {

    public final void start() {
        System.out.println("-------------------");
        run();
        System.out.println("-------------------");
    }

    protected abstract void run();
}
