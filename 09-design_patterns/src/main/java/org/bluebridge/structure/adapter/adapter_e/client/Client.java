package org.bluebridge.structure.adapter.adapter_e.client;

import structure.adapter.adapter_e.controller.XxjlController;
import structure.adapter.adapter_e.domain.Xxjl;

/**
 * 客户端 - 适配器模式
 *
 * @author lingwh
 * @date 2019/7/29 15:04
 */
public class Client {

    private static XxjlController xxjlController = new XxjlController();

    public static void main(String[] args) {
        xxjlController.save(new Xxjl());
        xxjlController.delete(new Xxjl());
    }
}
