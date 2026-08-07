package org.bluebridge.section_15_jdk15.unit_05_datetime;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * JDK 15 日期时间 API 测试
 * JDK 15 对 java.time 包进行了持续改进，Instant 支持纳秒级精度
 * 同时测试 YearMonth、Year、MonthDay 等日期时间类的使用
 *
 * 演化历程: 该文件测试的是 JDK 8 日期时间 API（java.time，JSR 310）
 *
 * @author lingwh
 * @date 2026/08/06 02:19
 */
public class DateTimeTest {

    /**
     * 测试 Instant.now() 获取纳秒精度时间戳
     * JDK 15 中 Instant 支持纳秒级精度
     */
    @Test
    public void testInstantNanoPrecision() {
        // 获取当前时间戳（纳秒精度）
        Instant now = Instant.now();

        System.out.println("Instant.now(): " + now);
        System.out.println("epoch seconds: " + now.getEpochSecond());
        System.out.println("nano adjustment: " + now.getNano());
        System.out.println("toEpochMilli: " + now.toEpochMilli());

        // 创建指定时刻的 Instant
        Instant epoch = Instant.EPOCH;
        System.out.println("EPOCH: " + epoch);

        // 从毫秒创建 Instant
        Instant fromMillis = Instant.ofEpochMilli(1600000000000L);
        System.out.println("from 1600000000000ms: " + fromMillis);

        // Instant 比较
        System.out.println("now is after epoch: " + now.isAfter(epoch));
        System.out.println("now is before epoch: " + now.isBefore(epoch));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 YearMonth 类的使用
     * YearMonth 表示年-月，可用于信用卡到期日等场景
     */
    @Test
    public void testYearMonth() {
        // 当前年月
        YearMonth currentYearMonth = YearMonth.now();
        System.out.println("当前 YearMonth: " + currentYearMonth);

        // 创建指定年月
        YearMonth yearMonth = YearMonth.of(2026, Month.AUGUST);
        System.out.println("指定 YearMonth: " + yearMonth);
        System.out.println("年份: " + yearMonth.getYear());
        System.out.println("月份: " + yearMonth.getMonth());
        System.out.println("月份值: " + yearMonth.getMonthValue());
        System.out.println("该月天数: " + yearMonth.lengthOfMonth());
        System.out.println("该年天数: " + yearMonth.lengthOfYear());
        System.out.println("是否为闰年: " + yearMonth.isLeapYear());

        // YearMonth 运算
        YearMonth nextMonth = yearMonth.plusMonths(1);
        System.out.println("下个月: " + nextMonth);
        YearMonth previousYear = yearMonth.minusYears(1);
        System.out.println("上一年: " + previousYear);

        // YearMonth 转换为 LocalDate
        LocalDate firstDay = yearMonth.atDay(1);
        LocalDate lastDay = yearMonth.atEndOfMonth();
        System.out.println("当月第一天: " + firstDay);
        System.out.println("当月最后一天: " + lastDay);
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 Year 类的使用
     */
    @Test
    public void testYear() {
        // 当前年份
        Year currentYear = Year.now();
        System.out.println("当前 Year: " + currentYear);

        // 创建指定年份
        Year year = Year.of(2026);
        System.out.println("指定 Year: " + year);
        System.out.println("值: " + year.getValue());
        System.out.println("是否为闰年: " + year.isLeapYear());
        System.out.println("长度: " + year.length() + " 天");

        // 判断某个日期是否在指定年份之后
        LocalDate date = LocalDate.of(2026, 8, 5);
        System.out.println("日期 " + date + " 在年份 " + year + " 之后: " + year.isAfter(Year.from(date)));

        // Year 运算
        Year nextYear = year.plusYears(1);
        System.out.println("明年: " + nextYear);
        Year previousYear = year.minusYears(1);
        System.out.println("去年: " + previousYear);

        // Year 转换为 LocalDate
        LocalDate firstDay = year.atDay(1);
        LocalDate lastDay = year.atDay(year.length());
        LocalDate atMonthDay = year.atMonthDay(MonthDay.of(8, 5));
        System.out.println("第一天: " + firstDay);
        System.out.println("最后一天: " + lastDay);
        System.out.println("8月5日: " + atMonthDay);
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 MonthDay 类的使用
     * MonthDay 表示月-日，可用于生日、纪念日等场景
     */
    @Test
    public void testMonthDay() {
        // 当前月日
        MonthDay currentMonthDay = MonthDay.now();
        System.out.println("当前 MonthDay: " + currentMonthDay);

        // 创建指定月日
        MonthDay monthDay = MonthDay.of(Month.AUGUST, 5);
        System.out.println("指定 MonthDay: " + monthDay);
        System.out.println("月份: " + monthDay.getMonth());
        System.out.println("月份值: " + monthDay.getMonthValue());
        System.out.println("日: " + monthDay.getDayOfMonth());

        // 验证 MonthDay 是否有效
        System.out.println("是否为有效日期: " + monthDay.isValidYear(2026));

        // MonthDay 比较
        MonthDay newYear = MonthDay.of(1, 1);
        System.out.println("8月5日 是否在 1月1日 之后: " + monthDay.isAfter(newYear));
        System.out.println("8月5日 是否在 1月1日 之前: " + monthDay.isBefore(newYear));

        // MonthDay 转换为 LocalDate
        LocalDate atYear = monthDay.atYear(2026);
        System.out.println("在 2026 年: " + atYear);
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 LocalDate 和 LocalTime 的常见用法
     */
    @Test
    public void testLocalDateAndTime() {
        // 当前日期和时间
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        System.out.println("今天: " + today);
        System.out.println("当前时间: " + now);

        // 日期计算
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate nextMonth = today.plusMonths(1);
        LocalDate nextYear = today.plusYears(1);
        System.out.println("下周: " + nextWeek);
        System.out.println("下月: " + nextMonth);
        System.out.println("明年: " + nextYear);

        // 使用 TemporalAdjusters
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println("本月第一天: " + firstDayOfMonth);
        System.out.println("本月最后一天: " + lastDayOfMonth);
        System.out.println("下周一: " + nextMonday);

        // 日期差计算
        long daysBetween = ChronoUnit.DAYS.between(firstDayOfMonth, lastDayOfMonth);
        long monthsBetween = ChronoUnit.MONTHS.between(firstDayOfMonth, lastDayOfMonth);
        System.out.println("本月天数: " + (daysBetween + 1) + " 天");
        System.out.println("月差: " + monthsBetween + " 月");
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 ZonedDateTime 时区处理
     */
    @Test
    public void testZonedDateTime() {
        // 获取不同时区的当前时间
        ZonedDateTime shanghai = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime tokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        ZonedDateTime newYork = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime london = ZonedDateTime.now(ZoneId.of("Europe/London"));

        System.out.println("上海时间: " + shanghai);
        System.out.println("东京时间: " + tokyo);
        System.out.println("纽约时间: " + newYork);
        System.out.println("伦敦时间: " + london);

        // 时区转换
        ZonedDateTime shanghaiTime = ZonedDateTime.of(
                LocalDateTime.of(2026, 8, 5, 19, 11),
                ZoneId.of("Asia/Shanghai")
        );
        ZonedDateTime tokyoTime = shanghaiTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        System.out.println("上海 2026-08-05 19:11 对应东京时间: " + tokyoTime);
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试日期时间格式化和解析
     */
    @Test
    public void testDateTimeFormatting() {
        // 格式化当前日期时间
        LocalDateTime now = LocalDateTime.now();

        // 使用预定义格式
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        System.out.println("ISO 格式: " + now.format(isoFormatter));

        // 自定义格式
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("自定义格式: " + now.format(customFormatter));

        DateTimeFormatter chineseFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        System.out.println("中文格式: " + now.format(chineseFormatter));

        // 解析字符串
        String dateStr = "2026-08-05 19:11:00";
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parsed = LocalDateTime.parse(dateStr, parser);
        System.out.println("解析结果: " + parsed);
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 Instant 与 LocalDateTime 之间的转换
     */
    @Test
    public void testInstantConversion() {
        // Instant 转 LocalDateTime
        Instant instant = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println("Instant: " + instant);
        System.out.println("LocalDateTime: " + localDateTime);

        // LocalDateTime 转 Instant
        LocalDateTime now = LocalDateTime.now();
        Instant fromLocalDateTime = now.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println("LocalDateTime: " + now);
        System.out.println("转换为 Instant: " + fromLocalDateTime);

        // Instant 加减操作
        Instant plusSeconds = instant.plus(10, ChronoUnit.SECONDS);
        Instant plusMillis = instant.plusMillis(500);
        Instant plusNanos = instant.plusNanos(1000);
        System.out.println("+10秒: " + plusSeconds);
        System.out.println("+500毫秒: " + plusMillis);
        System.out.println("+1000纳秒: " + plusNanos);

        // 计算两个 Instant 之间的时间差
        long secondsDiff = ChronoUnit.SECONDS.between(instant, plusSeconds);
        long nanosDiff = ChronoUnit.NANOS.between(instant, plusNanos);
        System.out.println("时间差(秒): " + secondsDiff);
        System.out.println("时间差(纳秒): " + nanosDiff);
        System.out.println("--- 分割线 ---");
    }
}