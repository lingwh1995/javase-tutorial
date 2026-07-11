package create.abstractfactory.abstractfactory_k;

/**
 * ProductFactory
 *
 * @author lingwh
 * @date
 */
public interface IProductFactory {
    // 生产手机
    IPhoneProduct phoneProduct();

    // 生成路由器
    IRouterProduct routerProduct();
}
