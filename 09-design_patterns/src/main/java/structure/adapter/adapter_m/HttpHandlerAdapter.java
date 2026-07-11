package structure.adapter.adapter_m;

/**
 * @author lingwh
 * @desc HTTP处理器适配器
 * @date 2026/7/9 00:00
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
