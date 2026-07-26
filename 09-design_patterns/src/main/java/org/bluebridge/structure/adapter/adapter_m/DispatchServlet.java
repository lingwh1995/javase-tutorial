package org.bluebridge.structure.adapter.adapter_m;

import java.util.ArrayList;
import java.util.List;

/**
 * 分发器
 *
 * @author lingwh
 * @date 2026/7/22 13:16
 */
public class DispatchServlet {

    public static List<HandlerAdapter> handlerAdapters = new ArrayList<HandlerAdapter>();

    public DispatchServlet() {
        handlerAdapters.add(new AnnotationHandlerAdapter());
        handlerAdapters.add(new HttpHandlerAdapter());
        handlerAdapters.add(new SimpleHandlerAdapter());
    }

    public void doDispatch() {
        // 此处模拟 SpringMVC 从 request 取 handler 的对象，仅仅 new 出，可以出，
        // 不论实现何种 Controller，适配器总能经过适配以后得到想要的结果
        // 模拟具体的 controller
        SimpleController controller = new SimpleController();
        // 得到对应适配器
        HandlerAdapter adapter = getHandler(controller);
        // 通过适配器执行对应的 controller 对应方法
        adapter.handle(controller);
    }

    public HandlerAdapter getHandler(Controller controller) {
        for (HandlerAdapter adapter : this.handlerAdapters) {
            if (adapter.supports(controller)) {
                return adapter;
            }
        }
        return null;
    }
}
