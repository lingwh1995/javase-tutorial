package org.bluebridge.test;

import org.bluebridge.enumeration.TimeUnitStrategyEnum;

/**
 * 日期时间工具类
 *
 * @author lingwh
 * @date 2026/1/10 11:28
 */
public class DateTimeUtils {

    public static void main(String[] args) {
        long duration = 10;
        String unit = "M";
        long expiration = calculateExpiration(duration, unit);
        System.out.println("过期时间点：" + expiration);
        String formattedDuration = formatDuration(duration, unit);
        System.out.println("格式化后的时长：" + formattedDuration);
    }

    /**
     * 计算过期时间点（当前时间 + 指定单位长度） 常用于 Redis 缓存过期设置
     */
    public static long calculateExpiration(long duration, String unit) {
        TimeUnitStrategyEnum strategy = TimeUnitStrategyEnum.of(unit);
        long millis = strategy.toMillis(duration);
        return System.currentTimeMillis() + millis;
    }

    /**
     * 格式化输出时长
     */
    public static String formatDuration(long duration, String unit) {
        TimeUnitStrategyEnum strategy = TimeUnitStrategyEnum.of(unit);
        return duration + " " + strategy.name().toLowerCase();
    }
}
