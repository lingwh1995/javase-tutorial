package structure.adapter.adapter_m;

/**
 * 注解处理器适配器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
