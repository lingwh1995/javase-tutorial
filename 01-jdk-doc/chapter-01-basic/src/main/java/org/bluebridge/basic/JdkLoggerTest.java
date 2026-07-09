package org.bluebridge.basic;

import org.junit.Test;

import java.util.logging.Logger;

/**
 * @author lingwh
 * @desc jdk自带日志框架测试
 * @date 2019/3/12 16:58
 */
public class JdkLoggerTest {

    // 创建一个日志对象
    private static Logger logger = Logger.getGlobal();

    @Test
    public void testJdkLogger() {
        logger.info("info......");
        logger.warning("warning......");
        logger.fine("fine......");
        logger.severe("severe......");
    }
}
