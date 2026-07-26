package org.bluebridge.structure.adapter.adapter_f.client;

import org.bluebridge.structure.adapter.adapter_f.controller.XxjlController;
import org.bluebridge.structure.adapter.adapter_f.domain.Xxjl;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/7/22 19:07
 */
public class Client {

    private static XxjlController xxjlController = new XxjlController();

    public static void main(String[] args) {
        xxjlController.save(new Xxjl());
        xxjlController.delete(new Xxjl());
    }
}
