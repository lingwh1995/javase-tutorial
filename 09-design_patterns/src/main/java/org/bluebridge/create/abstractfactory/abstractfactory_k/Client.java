package org.bluebridge.create.abstractfactory.abstractfactory_k;

public class Client {

    public static void main(String[] args) {

        System.out.println("------------小米产品------------");
        // 创建小米工厂
        IProductFactory xiaomiFactory = new XiaomiFactory();

        // 生产小米手机
        IPhoneProduct xiaomiPhone = xiaomiFactory.phoneProduct();
        xiaomiPhone.start();
        xiaomiPhone.sendSMS();

        // 生产小米路由器
        IRouterProduct xiaomiRouter = xiaomiFactory.routerProduct();
        xiaomiRouter.openwifi();
        xiaomiRouter.setting();

        System.out.println("------------华为产品------------");
        // 创建华为工厂
        IProductFactory huaweiFactory = new HuaweiFactory();

        // 生产华为手机
        IPhoneProduct huaweiPhone = huaweiFactory.phoneProduct();
        huaweiPhone.start();
        huaweiPhone.sendSMS();

        // 生产华为路由器
        IRouterProduct huaweiRouter = huaweiFactory.routerProduct();
        huaweiRouter.openwifi();
        huaweiRouter.setting();
    }
}
