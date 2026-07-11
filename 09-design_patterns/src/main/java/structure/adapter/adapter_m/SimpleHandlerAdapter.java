package structure.adapter.adapter_m;

/**
 * @author lingwh
 * @desc 简单处理器适配器
 * @date 2026/7/9 00:00
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
