package org.bluebridge.structure.adapter.adapter_m;

/**
 * 处理器适配器接口
 *
 * @author lingwh
 * @date 2026/7/22 10:38
 */
public interface HandlerAdapter {
    boolean supports(Object handler);

    void handle(Object handler);
}
