package cn.itcast.n5;

/**
 * volatile测试
 *
 * @author lingwh
 * @date 2026/7/13 19:02
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
