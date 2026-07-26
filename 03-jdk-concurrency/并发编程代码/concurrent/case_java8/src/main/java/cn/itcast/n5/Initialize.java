package cn.itcast.n5;

/**
 * 初始化测试
 *
 * @author lingwh
 * @date 2025/2/7 14:18
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
