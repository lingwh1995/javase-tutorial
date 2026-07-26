package cn.itcast.monitor.vo;

import lombok.Data;

/**
 * 监控信息
 *
 * @author lingwh
 * @date 2025/2/7 09:58
 */
@Data
public class Info {

    private long free;
    private long total;
    private long max;
    private long time;
}
