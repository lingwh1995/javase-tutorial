package org.bluebridge.structure.adapter.adapter_m;

/**
 * 适配器模式客户端
 *
 * @author lingwh
 * @date 2026/7/22 14:43
 */
public class Client {

    public static void main(String[] args) {
        DispatchServlet dispatchServlet = new DispatchServlet();
        dispatchServlet.doDispatch();
    }
}
