package org.bluebridge.structure.adapter.adapter_m;

/**
 * 简单处理器适配器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
