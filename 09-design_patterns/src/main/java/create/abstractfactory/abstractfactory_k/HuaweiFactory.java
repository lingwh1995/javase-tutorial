package create.abstractfactory.abstractfactory_k;

/**
 * 华为工厂实现类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
