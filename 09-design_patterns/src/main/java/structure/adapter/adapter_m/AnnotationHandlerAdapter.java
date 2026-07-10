package structure.adapter.adapter_m;

/**
 * @author lingwh
 * @desc 注解处理器适配器
 * @date 2026/7/9 00:00
 */
public class AnnotationHandlerAdapter implements HandlerAdapter {

    @Override
    public void handle(Object handler) {
        ((AnnotationController) handler).doAnnotationHandler();
    }

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof AnnotationController);
    }
}
