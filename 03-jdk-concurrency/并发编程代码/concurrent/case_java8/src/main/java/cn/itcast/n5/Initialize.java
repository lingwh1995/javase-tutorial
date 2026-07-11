package cn.itcast.n5;

/**
 * @author lingwh
 * @desc 初始化测试
 * @date 2026/7/9 00:00
 */
public class Initialize {

    private boolean initialized = false;

    public void init() {

        synchronized (this) {
            if (initialized) {
                return;
            }
            doInit();
            initialized = true;
        }
    }

    private void doInit() {

    }
}
