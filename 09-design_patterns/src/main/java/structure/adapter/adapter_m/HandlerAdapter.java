package structure.adapter.adapter_m;

/**
 * @author lingwh
 * @desc 处理器适配器接口
 * @date 2026/7/9 00:00
 */
public interface HandlerAdapter {
    boolean supports(Object handler);

    void handle(Object handler);
}
