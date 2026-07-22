package org.bluebridge.structure.decorator.decorator_i.client;

import org.bluebridge.structure.decorator.decorator_i.controller.GoodsSaleController;

/**
 * 模拟客户端
 *
 * @author lingwh
 * @date 2019/8/7 9:50
 */
public class Client {

    public static void main(String[] args) {
        GoodsSaleController goodsSaleController = new GoodsSaleController();
        goodsSaleController.sale();
    }
}
