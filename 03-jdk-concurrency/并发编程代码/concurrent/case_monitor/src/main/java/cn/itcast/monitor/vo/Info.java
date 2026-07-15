package cn.itcast.monitor.vo;

import lombok.Data;

/**
 * 监控信息
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
@Data
public class Info {

    private long free;
    private long total;
    private long max;
    private long time;
}
