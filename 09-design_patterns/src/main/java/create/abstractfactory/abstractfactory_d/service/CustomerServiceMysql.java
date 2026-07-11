package create.abstractfactory.abstractfactory_d.service;

import create.abstractfactory.abstractfactory_d.domain.Cusomer;

/**
 * @author lingwh
 * @desc Mysql客户服务实现
 * @date 2026/7/9 00:00
 */
public class CustomerServiceMysql implements ICustomerService {

    @Override
    public Cusomer get(Integer uid) {
        System.out.println("Mysql操作数据库......Customer");
        return new Cusomer();
    }
}
