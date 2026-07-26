package test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap 测试
 *
 * @author lingwh
 * @date 2025/2/7 09:15
 */
public class TestConcurrentHashMap {

    public static void main(String[] args) {
        new ConcurrentHashMap(16, 0.75f, 16);
    }
}
