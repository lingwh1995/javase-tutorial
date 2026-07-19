package create.abstractfactory.abstractfactory_k;

/**
 * 小米工厂实现类
 *
 * @author lingwh
 * @date 2019/10/18 13:27
 */
public class XiaomiFactory implements IProductFactory {

    @Override
    public IPhoneProduct phoneProduct() {
        return new XiaomiPhone();
    }

    @Override
    public IRouterProduct routerProduct() {
        return new XiaomiRouter();
    }
}
