package org.bluebridge.create.abstractfactory.abstractfactory_d.service;

import org.bluebridge.create.abstractfactory.abstractfactory_d.domain.Cusomer;

/**
 * Mysql 客户服务实现
 *
 * @author lingwh
 * @date 2026/7/22 15:39
 */
public class CustomerServiceMysql implements ICustomerService {

    @Override
    public Cusomer get(Integer uid) {
        System.out.println("Mysql操作数据库......Customer");
        return new Cusomer();
    }
}
