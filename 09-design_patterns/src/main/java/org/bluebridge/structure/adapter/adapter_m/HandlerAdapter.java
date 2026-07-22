package org.bluebridge.structure.adapter.adapter_m;

/**
 * 处理器适配器接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface HandlerAdapter {
    boolean supports(Object handler);

    void handle(Object handler);
}
