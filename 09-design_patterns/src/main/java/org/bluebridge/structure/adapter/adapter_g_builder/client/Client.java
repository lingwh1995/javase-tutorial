package org.bluebridge.structure.adapter.adapter_g_builder.client;

import org.bluebridge.structure.adapter.adapter_g_builder.controller.XxjlController;
import org.bluebridge.structure.adapter.adapter_g_builder.domain.Xxjl;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/7/22 17:53
 */
public class Client {

    private static XxjlController xxjlController = new XxjlController();

    public static void main(String[] args) {
        xxjlController.save(new Xxjl());
        xxjlController.delete(new Xxjl());
    }
}
