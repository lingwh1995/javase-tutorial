package org.bluebridge.section_01_basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Runtime 测试
 *
 * @author lingwh
 * @date 2025/9/30 10:41
 */
@Slf4j
public class RunTimeTest {

    /**
     * 获取可用处理器核心数
     * 该方法返回的是 JVM 可用的处理器核心数，通常情况下它与物理 CPU 核心数一致。如容器化环境下，获取到的值可能不正确。
     */
    @Test
    public void testGetAvailableProcessors() {
        // 获取可用处理器核心数
        int coreCount = Runtime.getRuntime().availableProcessors();
        log.info("可用处理器核心数: {}", coreCount);
    }
}
