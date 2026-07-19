package create.abstractfactory.abstractfactory_k;

/**
 * 小米路由器实现类
 *
 * @author lingwh
 * @date 2019/10/18 13:36
 */
public class XiaomiRouter implements IRouterProduct {

    @Override
    public void start() {
        System.out.println("开启小米路由器");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭小米路由器");
    }

    @Override
    public void openwifi() {
        System.out.println("打开小米wifi");
    }

    @Override
    public void setting() {
        System.out.println("设置小米路由器");
    }
}
