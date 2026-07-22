package org.bluebridge.expand.designpattern.filter.filter_a;

import java.util.List;

/**
 * 抽象的过滤器
 *
 * @author lingwh
 * @date 2019/7/29 15:52
 */
public interface Filter {

    /**
     * @param consumers 消费者
     * @return
     */
    List<Consumer> filter(List<Consumer> consumers);
}
