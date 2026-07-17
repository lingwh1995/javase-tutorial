package org.bluebridge.thread.thread_designpattern.balking.balking_b;

/**
 * Balking 模式初始化示例
 *
 * @author lingwh
 * @date 2019/10/17 9:48
 */
public class Something {

    private boolean initialized = false;

    public synchronized void init() {
        if (initialized) {
            System.out.println("已经执行过了初始化操作,不能在进行初始化操作了......");
            return;
        }
        doInit();
        initialized = true;
    }

    private void doInit() {
        // 实际的初始化处理
        System.out.println("执行了初始化处理......");
    }
}
