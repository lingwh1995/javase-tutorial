package org.bluebridge.java8.section_07_datetime;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Java8 引入了全新的时间处理类库，解决了旧版类库中线程不安全的问题
 *
 * @author lingwh
 * @date 2025/9/28 14:09
 */
@Slf4j
public class DateTimeTest {

    /**
     * 日期时间转为字符串
     */
    @Test
    public void testDateTimeToString() {
        String patternOfh12 = "yyyy-MM-dd hh:mm:ss";
        String patternOfh24 = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternOfh12);
        String formattedDate = LocalDateTime.now().format(formatter);
        log.info("12小时格式：{}", formattedDate);

        formatter = DateTimeFormatter.ofPattern(patternOfh24);
        formattedDate = LocalDateTime.now().format(formatter);
        log.info("24小时格式：{}", formattedDate);
    }

    /**
     * 字符串转为日期时间
     */
    @Test
    public void testStringToDateTime() {
        DateTimeFormatter formatterOfDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2023-10-01", formatterOfDate);

        DateTimeFormatter formatterOfDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("2023-10-01 14:30:00", formatterOfDateTime);
    }
}
