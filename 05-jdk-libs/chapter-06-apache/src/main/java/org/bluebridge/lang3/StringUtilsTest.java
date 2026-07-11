package org.bluebridge.lang3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 字符串工具类
 * @date 2026/7/9 00:00
 */
@Slf4j
public class StringUtilsTest {

    @Test
    public void testStringUtils() {
        String s = "";
        log.debug("{}", StringUtils.isBlank(s));
    }
}
