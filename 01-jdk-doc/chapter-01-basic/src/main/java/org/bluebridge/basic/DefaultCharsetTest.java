package org.bluebridge.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 默认字符集测试
 * @date 2025/7/3 16:58
 */
@Slf4j
public class DefaultCharsetTest {

    @Test
    public void testDefaultCharset() {
        String charsetName = System.getProperty("file.encoding");
        log.info("Default Charset: {}", charsetName);
    }
}
