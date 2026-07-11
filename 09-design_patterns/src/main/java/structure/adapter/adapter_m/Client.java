package structure.adapter.adapter_m;

/**
 * @author lingwh
 * @desc 适配器模式客户端
 * @date 2026/7/9 00:00
 */
public class Client {
    public static void main(String[] args) {
        DispatchServlet dispatchServlet = new DispatchServlet();
        dispatchServlet.doDispatch();
    }
}
