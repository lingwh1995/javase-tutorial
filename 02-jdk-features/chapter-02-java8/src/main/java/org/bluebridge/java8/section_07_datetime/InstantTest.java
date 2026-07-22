package org.bluebridge.java8.section_07_datetime;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * 测试Instant类
 *
 * @author lingwh
 * @date 2025/11/14 11:40
 */
@Slf4j
public class InstantTest {

    @Test
    public void testInstant() {
        // 时刻点
        Instant time1 = Instant.now();
        log.info("当前时间：{}", time1);
        // 本地日期时间
        LocalDateTime time2 = LocalDateTime.now();
        log.info("当前时间：{}", time2);
        // 带时区的日期时间
        ZonedDateTime time3 = ZonedDateTime.now();
        log.info("当前时间：{}", time3);
    }
}
