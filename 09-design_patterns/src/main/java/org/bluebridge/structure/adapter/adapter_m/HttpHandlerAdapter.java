package org.bluebridge.structure.adapter.adapter_m;

/**
 * HTTP 处理器适配器
 *
 * @author lingwh
 * @date 2026/7/22 08:14
 */
public class HttpHandlerAdapter implements HandlerAdapter {

    @Override
    public void handle(Object handler) {
        ((HttpController) handler).doHttpHandler();
    }

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof HttpController);
    }
}
