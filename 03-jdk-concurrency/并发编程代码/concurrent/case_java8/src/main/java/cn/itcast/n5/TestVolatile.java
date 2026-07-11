package cn.itcast.n5;

/**
 * @author lingwh
 * @desc volatile测试
 * @date 2026/7/9 00:00
 */
public class TestVolatile {

    volatile boolean initialized = false;

    void init() {
        if (initialized) {
            return;
        }
        doInit();
        initialized = true;
    }

    private void doInit() {

    }
}
