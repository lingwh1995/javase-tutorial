package org.bluebridge.enumeration;

/**
 * 时间单位换算策略枚举
 *
 * @author lingwh
 * @date 2026/1/10 11:23
 */
public enum TimeUnitStrategyEnum {

    SECONDS("S", "秒") {
        @Override
        public long toMillis(long duration) {
            return duration * 1000;
        }
    },
    MINUTES("M", "分") {
        @Override
        public long toMillis(long duration) {
            return duration * 60 * 1000;
        }
    },
    HOURS("H", "小时") {
        @Override
        public long toMillis(long duration) {
            return duration * 60 * 60 * 1000;
        }
    },
    DAYS("D", "天") {
        @Override
        public long toMillis(long duration) {
            return duration * 24 * 60 * 60 * 1000;
        }
    };

    private final String code;
    private final String description;

    TimeUnitStrategyEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 抽象方法：将当前单位数值转换为毫秒
     *
     * @param duration
     * @return
     */
    public abstract long toMillis(long duration);

    public String getCode() {
        return code;
    }

    /**
     * 静态查找方法
     *
     * @param code
     * @return
     */
    public static TimeUnitStrategyEnum of(String code) {
        for (TimeUnitStrategyEnum strategy : values()) {
            if (strategy.code.equalsIgnoreCase(code)) {
                return strategy;
            }
        }
        throw new IllegalArgumentException("未知的时间单位: " + code);
    }
}
