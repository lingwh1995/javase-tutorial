package cn.itcast.monitor.vo;

import lombok.Data;

/**
 * @author lingwh
 * @desc 监控信息
 * @date 2026/7/9 00:00
 */
@Data
public class Info {
    private long free;
    private long total;
    private long max;
    private long time;
}
