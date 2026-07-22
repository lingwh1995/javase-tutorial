package org.bluebridge.create.abstractfactory.abstractfactory_k;

/**
 * ProductFactory
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface IProductFactory {

    // 生产手机
    IPhoneProduct phoneProduct();

    // 生成路由器
    IRouterProduct routerProduct();
}
