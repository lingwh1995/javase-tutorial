package org.bluebridge.create.abstractfactory.abstractfactory_k;

/**
 * 华为工厂实现类
 *
 * @author lingwh
 * @date 2026/7/22 10:38
 */
public class HuaweiFactory implements IProductFactory {

    @Override
    public IPhoneProduct phoneProduct() {
        return new HuaweiPhone();
    }

    @Override
    public IRouterProduct routerProduct() {
        return new HuaweiRouter();
    }
}
