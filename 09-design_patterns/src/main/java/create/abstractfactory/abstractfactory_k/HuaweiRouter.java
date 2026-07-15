package create.abstractfactory.abstractfactory_k;

/**
 * 华为路由器实现类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class HuaweiRouter implements IRouterProduct {

    @Override
    public void start() {
        System.out.println("开启华为路由器");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭华为路由器");
    }

    @Override
    public void openwifi() {
        System.out.println("打开华为wifi");
    }

    @Override
    public void setting() {
        System.out.println("设置华为路由器");
    }
}
