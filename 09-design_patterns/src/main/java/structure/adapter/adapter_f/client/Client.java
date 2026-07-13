package structure.adapter.adapter_f.client;

import structure.adapter.adapter_f.controller.XxjlController;
import structure.adapter.adapter_f.domain.Xxjl;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Client {

    private static XxjlController xxjlController = new XxjlController();

    public static void main(String[] args) {
        xxjlController.save(new Xxjl());
        xxjlController.delete(new Xxjl());
    }
}
