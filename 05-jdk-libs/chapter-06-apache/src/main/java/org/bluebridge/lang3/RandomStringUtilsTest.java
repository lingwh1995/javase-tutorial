package org.bluebridge.lang3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * 随机字符串工具类-已经过期，不推荐使用，使用 commons-text 包中 RandomStringGenerator 代替
 *
 * @author lingwh
 * @date 2025/9/15 11:15
 */
@Slf4j
public class RandomStringUtilsTest {

    /**
     * 使用 RandomStringUtils 生成长度为 6 位的随机验证码
     */
    @Test
    public void testRandomStringUtils() {
        String randomCode = RandomStringUtils.randomNumeric(6);
        log.debug("{}", randomCode);
    }
}
