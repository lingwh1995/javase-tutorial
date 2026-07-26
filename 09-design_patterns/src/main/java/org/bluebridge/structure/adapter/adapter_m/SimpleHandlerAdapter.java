package org.bluebridge.structure.adapter.adapter_m;

/**
 * 简单处理器适配器
 *
 * @author lingwh
 * @date 2026/7/22 15:29
 */
public class SimpleHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof SimpleController);
    }

    @Override
    public void handle(Object handler) {
        ((SimpleController) handler).doSimplerHandler();
    }
}
