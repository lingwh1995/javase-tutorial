package create.abstractfactory.abstractfactory_d.service;

import create.abstractfactory.abstractfactory_d.domain.Cusomer;

/**
 * Mysql客户服务实现
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class CustomerServiceMysql implements ICustomerService {

    @Override
    public Cusomer get(Integer uid) {
        System.out.println("Mysql操作数据库......Customer");
        return new Cusomer();
    }
}
