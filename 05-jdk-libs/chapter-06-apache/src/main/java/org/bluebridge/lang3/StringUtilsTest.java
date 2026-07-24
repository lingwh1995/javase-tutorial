package org.bluebridge.lang3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * 字符串工具类
 *
 * @author lingwh
 * @date 2019/7/26 10:30
 */
@Slf4j
public class StringUtilsTest {

    @Test
    public void testStringUtils() {
        String s = "";
        log.debug("{}", StringUtils.isBlank(s));
    }
}
