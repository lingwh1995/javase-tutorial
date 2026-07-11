package create.abstractfactory.abstractfactory_k;

/**
 * @author lingwh
 * @desc 华为工厂实现类
 * @date 2026/7/9 00:00
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
