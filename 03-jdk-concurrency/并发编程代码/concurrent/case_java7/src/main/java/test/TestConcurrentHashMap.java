package test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap测试
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TestConcurrentHashMap {

    public static void main(String[] args) {
        new ConcurrentHashMap(16, 0.75f, 16);
    }
}
