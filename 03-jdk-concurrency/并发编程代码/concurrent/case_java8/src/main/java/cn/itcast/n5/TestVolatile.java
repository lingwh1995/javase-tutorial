package cn.itcast.n5;

/**
 * volatile 测试
 *
 * @author lingwh
 * @date 2025/2/7 17:15
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
