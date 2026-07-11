package test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lingwh
 * @desc ConcurrentHashMap测试
 * @date 2026/7/9 00:00
 */
public class TestConcurrentHashMap {
    public static void main(String[] args) {
        new ConcurrentHashMap(16, 0.75f, 16);
    }
}
